package ru.androidschool.intensiv.presentation.feed

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function3
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.android.synthetic.main.feed_header.*
import kotlinx.android.synthetic.main.search_toolbar.view.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.db.model_db.entities_db.Category
import ru.androidschool.intensiv.data.mappers.MapperDbToVo
import ru.androidschool.intensiv.data.mappers.MapperToMovieDb
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieAndCategoryCrossRef
import ru.androidschool.intensiv.data.repository.db_repository.DBMovieRepository
import ru.androidschool.intensiv.data.repository.remote_repository.NowPlayingMoviesRemoteRepository
import ru.androidschool.intensiv.data.repository.remote_repository.PopularMoviesRemoteRepository
import ru.androidschool.intensiv.data.repository.remote_repository.UpcomingMoviesRemoteRepository
import ru.androidschool.intensiv.data.vo.Movie
import ru.androidschool.intensiv.data.db.database.MovieDatabase
import ru.androidschool.intensiv.domain.usecase.NowPlayingMoviesRemoteUseCase
import ru.androidschool.intensiv.domain.usecase.PopularMoviesRemoteUseCase
import ru.androidschool.intensiv.domain.usecase.UpcomingMoviesRemoteUseCase
import ru.androidschool.intensiv.domain.usecase.extensions.applySchedulers
import ru.androidschool.intensiv.domain.usecase.extensions.applyVisibilityProgressBar
import ru.androidschool.intensiv.data.db.model_db.CategoryWithMovies
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieDb
import ru.androidschool.intensiv.data.dto.movie_dto.MoviesApiResponseDto
import ru.androidschool.intensiv.data.mappers.MapperRemoteToVo
import ru.androidschool.intensiv.data.db.model_db.ResultFeedMovies
import ru.androidschool.intensiv.presentation.extension.afterTextChanged
import timber.log.Timber

class FeedFragment : Fragment(R.layout.feed_fragment) {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }
    private val nowPlayingMoviesRemoteUseCase = NowPlayingMoviesRemoteUseCase(
        NowPlayingMoviesRemoteRepository()
    )
    private val popularMoviesRemoteUseCase = PopularMoviesRemoteUseCase(
        PopularMoviesRemoteRepository()
    )
    private val upcomingMoviesRemoteUseCase = UpcomingMoviesRemoteUseCase(
        UpcomingMoviesRemoteRepository()
    )

    private lateinit var dbMovieRepository: DBMovieRepository
    private lateinit var disposable: Disposable
    private var compositeDisposable = CompositeDisposable()

    private val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    @SuppressLint("TimberArgCount")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search_toolbar.search_edit_text.afterTextChanged {
            Timber.d(it.toString())
            if (it.toString().length > MIN_LENGTH) {
                openSearch(it.toString())
            }
        }
        val database = MovieDatabase.getInstance(requireContext())
        dbMovieRepository = DBMovieRepository(database)
        initCategories()
        getMoviesFromDb()
    }

    @SuppressLint("TimberArgCount")
    private fun initCategories() {
        var categories = listOf<Category>()
        disposable = dbMovieRepository.getCategories()
            .applySchedulers()
            .subscribe({
                categories = it
                if (categories.isNullOrEmpty()) {
                    setCategories()
                }
            }, { error ->
                Timber.e("Error init categories", error.message)
            })
    }

    @SuppressLint("TimberArgCount")
    private fun setCategories() {
        val categories = arrayListOf<Category>()
        categories.add(Category(categoryId = getString(R.string.popular)))
        categories.add(Category(categoryId = getString(R.string.upcoming)))
        categories.add(Category(categoryId = getString(R.string.recommended)))
        disposable = dbMovieRepository.setCategories(categories)
            .applySchedulers()
            .subscribe({
                Timber.e("setCategories", "successfully")
            }, {
                Timber.e("setCategories error", it.message)
            })
        compositeDisposable.add(disposable)
    }

    @SuppressLint("TimberArgCount")
    private fun getMoviesFromDb() {
        val popularMovieFromDb = dbMovieRepository.getCategoryWithMoviesById(getString(R.string.popular))
        val nowPlayingMovieFromDb = dbMovieRepository.getCategoryWithMoviesById(getString(R.string.recommended))
        val upcomingMovieFromDb = dbMovieRepository.getCategoryWithMoviesById(getString(R.string.upcoming))

        val disposable =
            Single.zip(popularMovieFromDb, nowPlayingMovieFromDb, upcomingMovieFromDb,
                Function3<CategoryWithMovies, CategoryWithMovies, CategoryWithMovies, Map<MovieCategory, ResultFeedMovies>> { popularMovieFromDb, nowPlayingMovieFromDb, upcomingMovieFromDb ->
                    linkedMapOf(
                        MovieCategory.POPULAR to ResultFeedMovies(
                            R.string.popular,
                            popularMovieFromDb.movieDbs.map { MapperDbToVo.toViewObject(it) }),
                        MovieCategory.NOWPLAYING to ResultFeedMovies(
                            R.string.recommended,
                            nowPlayingMovieFromDb.movieDbs.map { MapperDbToVo.toViewObject(it) }),
                        MovieCategory.UPCOMING to ResultFeedMovies(
                            R.string.upcoming,
                            upcomingMovieFromDb.movieDbs.map { MapperDbToVo.toViewObject(it) })
                    )
                }
            )
                .applySchedulers()
                .applyVisibilityProgressBar(progress_feed)
                .subscribe({
                    createCardMovies(it)
                    getMoviesFromApi()
                }, { error ->
                    getMoviesFromApi()
                    Timber.d("Error movies from db", error.message)
                })
        compositeDisposable.add(disposable)
    }

    @SuppressLint("TimberArgCount")
    private fun getMoviesFromApi() {
        val popularMovie = popularMoviesRemoteUseCase.getMovies()
        val nowPlayingMovie = nowPlayingMoviesRemoteUseCase.getMovies()
        val upcomingMovie = upcomingMoviesRemoteUseCase.getMovies()

        val disposable =
            Single.zip(popularMovie, nowPlayingMovie, upcomingMovie,
            Function3<MoviesApiResponseDto, MoviesApiResponseDto, MoviesApiResponseDto, Map<MovieCategory, ResultFeedMovies>> { popularMovie, nowPlayingMovie, upcomingMovie ->
                linkedMapOf(
                    MovieCategory.POPULAR to ResultFeedMovies(R.string.popular, popularMovie.results.map { MapperRemoteToVo.toViewObject(it) }),
                    MovieCategory.NOWPLAYING to ResultFeedMovies(R.string.recommended, nowPlayingMovie.results.map { MapperRemoteToVo.toViewObject(it) }),
                    MovieCategory.UPCOMING to ResultFeedMovies(R.string.upcoming, upcomingMovie.results.map { MapperRemoteToVo.toViewObject(it) })
                )
            }
        )
            .applySchedulers()
            .applyVisibilityProgressBar(progress_feed)
            .subscribe({
                createCardMovies(it)
                val movies = setListMovie(it)
                val moviesByCat = setMoviesByCategories(it)
                saveMovies(movies, moviesByCat)
            }, { error ->
                Timber.d("Error", error.message)
            })
        compositeDisposable.add(disposable)
    }

    private fun saveMovies(
        movies: List<MovieDb>,
        moviesByCat: List<MovieAndCategoryCrossRef>
    ) {
        disposable = dbMovieRepository.setMovies(movies)
            .applySchedulers()
            .subscribe({
                saveMoviesByCategories(moviesByCat)
            }, { error ->
                println("${error.message}")
            })
        compositeDisposable.add(disposable)
    }


    @SuppressLint("TimberArgCount")
    private fun saveMoviesByCategories(moviesByCat: List<MovieAndCategoryCrossRef>) {
        disposable = dbMovieRepository.saveMoviesByCategories(moviesByCat)
            .applySchedulers()
            .subscribe({
                Timber.e("saveMoviesByCategories", "successfully")
            }, {
                println(it.message)
            })
        compositeDisposable.add(disposable)
    }

    private fun setMoviesByCategories(list: Map<MovieCategory, ResultFeedMovies>): List<MovieAndCategoryCrossRef> {

        val moviesByCat = arrayListOf<MovieAndCategoryCrossRef>()
        list.forEach() {
            it.value.movies.forEach { m ->
                val mov = MovieAndCategoryCrossRef(m.id, getString(it.value.titleRes))
                moviesByCat.add(mov)
            }
        }
        return moviesByCat
    }

    private fun setListMovie(
        movies: Map<MovieCategory, ResultFeedMovies>
    ): List<MovieDb> {
        val moviesDb = arrayListOf<MovieDb>()
        movies.forEach() {
            it.value.movies.forEach {m ->
               val mov = MapperToMovieDb.convertTo(m)
                moviesDb.add(mov)
            }
        }
        return moviesDb
    }

    private fun createCardMovies(movies: Map<MovieCategory, ResultFeedMovies>) {
        adapter.clear()
        for (movie in movies) {
            val listMovieItem = movie.value.movies.map {
                    MovieItem(it) { movie ->
                        openMovieDetails(
                            movie
                        )
                    }
                }.toList()
                val listCardContainer = listOf(listMovieItem.let {
                    MainCardContainer(movie.value.titleRes, it)
                })

                movies_recycler_view.adapter = adapter.apply {
                    addAll(listCardContainer)
                }
            }
        }

    private fun openMovieDetails(movie: Movie) {
        if (movie.id != null) {
            findNavController().navigate(
                FeedFragmentDirections.actionHomeDestToMovieDetailsFragment(
                    movie.id
                )
            )
        }
    }

    private fun openSearch(searchText: String) {
        val bundle = Bundle()
        bundle.putString(KEY_SEARCH, searchText)
        findNavController().navigate(R.id.search_dest, bundle, options)
    }

    override fun onStop() {
        super.onStop()
        search_toolbar.clear()
        adapter.clear()
        compositeDisposable.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    companion object {
        const val MIN_LENGTH = 3
        const val KEY_TITLE = "title"
        const val KEY_SEARCH = "search"
    }
}
