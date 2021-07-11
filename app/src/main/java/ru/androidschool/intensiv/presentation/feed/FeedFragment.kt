package ru.androidschool.intensiv.ui.feed

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
import ru.androidschool.intensiv.data.MapperDbToVo
import ru.androidschool.intensiv.data.MapperToMovieDb
import ru.androidschool.intensiv.data.MapperRemoteToVo
import ru.androidschool.intensiv.data.movies.DBMovieRepository
import ru.androidschool.intensiv.data.movies.MovieVo
import ru.androidschool.intensiv.data.movies.RemoteMovieRepository
import ru.androidschool.intensiv.database.MovieDatabase
import ru.androidschool.intensiv.extensions.applySchedulers
import ru.androidschool.intensiv.extensions.applyVisibilityProgressBar
import ru.androidschool.intensiv.model.db_movie_model.Category
import ru.androidschool.intensiv.model.db_movie_model.CategoryWithMovies
import ru.androidschool.intensiv.model.db_movie_model.Movie
import ru.androidschool.intensiv.model.db_movie_model.MovieAndCategoryCrossRef
import ru.androidschool.intensiv.model.movie_model.ApiResponse
import ru.androidschool.intensiv.model.movie_model.ResultFeedMovies
import ru.androidschool.intensiv.ui.afterTextChanged
import timber.log.Timber

class FeedFragment : Fragment(R.layout.feed_fragment) {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }
    private val nowPlayingMoviesRemoteUseCase = NowPlayingMoviesRemoteUseCase(NowPlayingMoviesRemoteRepository())
    private val popularMoviesRemoteUseCase = PopularMoviesRemoteUseCase(PopularMoviesRemoteRepository())
    private val upcomingMoviesRemoteUseCase = UpcomingMoviesRemoteUseCase(UpcomingMoviesRemoteRepository())
    private val remoteMovieRepository = RemoteMovieRepository()
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
                Function3<CategoryWithMovies, CategoryWithMovies, CategoryWithMovies, Map<MovieCategory, ResultFeedMovies<MovieVo>>> { popularMovieFromDb, nowPlayingMovieFromDb, upcomingMovieFromDb ->
                    linkedMapOf(
                        MovieCategory.POPULAR to ResultFeedMovies(
                            R.string.popular,
                            popularMovieFromDb.movies.map { MapperDbToVo.toViewObject(it) }),
                        MovieCategory.NOWPLAYING to ResultFeedMovies(
                            R.string.recommended,
                            nowPlayingMovieFromDb.movies.map { MapperDbToVo.toViewObject(it) }),
                        MovieCategory.UPCOMING to ResultFeedMovies(
                            R.string.upcoming,
                            upcomingMovieFromDb.movies.map { MapperDbToVo.toViewObject(it) })
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

        val disposable = Single.zip(popularMovie, nowPlayingMovie, upcomingMovie,
            Function3<ApiResponse, ApiResponse, ApiResponse, Map<MovieCategory, ResultFeedMovies<MovieVo>>> { popularMovie, nowPlayingMovie, upcomingMovie ->
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
                val mapper = MapperToMovieDb
                val movies = setListMovie(it, mapper)
                val moviesByCat = setMoviesByCategories(it)
                saveMovies(movies, moviesByCat)
            }, { error ->
                Timber.d("Error", error.message)
            })
        compositeDisposable.add(disposable)
    }

    private fun <T>createCardMovies(movies: Map<MovieCategory, ResultFeedMovies<T>>) {
        adapter.clear()
        for (movie in movies) {
            val listMovieItem = movie.value.movies.map {
                MovieItem(it as ResultApi) { movie ->
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

    private fun openMovieDetails(resultApi: MovieVo) {
        if (resultApi.id != null) {
            findNavController().navigate(
                FeedFragmentDirections.actionHomeDestToMovieDetailsFragment(
                    resultApi.id
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
