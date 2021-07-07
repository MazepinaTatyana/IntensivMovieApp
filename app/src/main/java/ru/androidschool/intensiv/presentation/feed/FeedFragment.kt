package ru.androidschool.intensiv.presentation.feed

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.android.synthetic.main.feed_header.*
import kotlinx.android.synthetic.main.search_toolbar.view.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.mappers.Mapper
import ru.androidschool.intensiv.data.repository.db_repository.DBMovieRepository
import ru.androidschool.intensiv.data.repository.remote_repository.NowPlayingMoviesRemoteRepository
import ru.androidschool.intensiv.data.repository.remote_repository.PopularMoviesRemoteRepository
import ru.androidschool.intensiv.data.repository.remote_repository.UpcomingMoviesRemoteRepository
import ru.androidschool.intensiv.data.vo.Movie
import ru.androidschool.intensiv.domain.usecase.NowPlayingMoviesRemoteUseCase
import ru.androidschool.intensiv.domain.usecase.PopularMoviesRemoteUseCase
import ru.androidschool.intensiv.domain.usecase.UpcomingMoviesRemoteUseCase
import ru.androidschool.intensiv.data.db.model_db.entities_db.Category
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieAndCategoryCrossRef
import ru.androidschool.intensiv.data.db.model_db.ResultFeedMovies
import ru.androidschool.intensiv.databinding.FeedFragmentBinding
import ru.androidschool.intensiv.databinding.MovieDetailsFragmentBinding
import ru.androidschool.intensiv.presentation.extension.applyVisibilityProgressBar
import ru.androidschool.intensiv.ui.afterTextChanged
import timber.log.Timber

class FeedFragment : Fragment(R.layout.feed_fragment) {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }
    private lateinit var feedFragmentBinding: FeedFragmentBinding

    private val nowPlayingMoviesRemoteUseCase = NowPlayingMoviesRemoteUseCase(NowPlayingMoviesRemoteRepository())
    private val popularMoviesRemoteUseCase = PopularMoviesRemoteUseCase(PopularMoviesRemoteRepository())
    private val upcomingMoviesRemoteUseCase = UpcomingMoviesRemoteUseCase(UpcomingMoviesRemoteRepository())

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

        feedFragmentBinding = FeedFragmentBinding.bind(view)
        search_toolbar.search_edit_text.afterTextChanged {
            Timber.d(it.toString())
            if (it.toString().length > MIN_LENGTH) {
                openSearch(it.toString())
            }
        }

        dbMovieRepository = DBMovieRepository(requireContext())
        initCategories()

        disposable = dbMovieRepository.getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .applyVisibilityProgressBar(feedFragmentBinding.progressFeed as ProgressBar)
            .subscribe({
                val a = it
            },{})
        compositeDisposable.add(disposable)

        getMoviesFromDb()
    }

    private fun initCategories() {
        var categories = listOf<Category>()
        disposable = dbMovieRepository.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                categories = it
                if (categories.isNullOrEmpty()) {
                    setCategories()
                }
            }, {
                println("lalllalalalal")
            })
    }

    private fun setCategories() {
        val categories = arrayListOf<Category>()
        categories.add(Category(categoryId = getString(R.string.popular)))
        categories.add(Category(categoryId = getString(R.string.upcoming)))
        categories.add(Category(categoryId = getString(R.string.recommended)))
        disposable = dbMovieRepository.setCategories(categories)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}
            , {})
    }

    @SuppressLint("TimberArgCount")
    private fun getMoviesFromDb() {

        dbMovieRepository.getCategoriesWithMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .applyVisibilityProgressBar(feedFragmentBinding.progressFeed as ProgressBar)
            .subscribe({
                val a = it
            },{})


        val popularMovieFromDb = dbMovieRepository.getCategoryWithMoviesById(getString(R.string.popular))
        val nowPlayingMovieFromDb = dbMovieRepository.getCategoryWithMoviesById(getString(R.string.recommended))
        val upcomingMovieFromDb = dbMovieRepository.getCategoryWithMoviesById(getString(R.string.upcoming))

        val disposable =
            Single.zip( popularMovieFromDb, nowPlayingMovieFromDb, upcomingMovieFromDb,
                { popularMovieFromDb, nowPlayingMovieFromDb, upcomingMovieFromDb ->
                    linkedMapOf(MovieCategory.POPULAR to ResultFeedMovies(R.string.popular, popularMovieFromDb.movies),
                        MovieCategory.NOWPLAYING to ResultFeedMovies(R.string.recommended, nowPlayingMovieFromDb.movies),
                        MovieCategory.UPCOMING to ResultFeedMovies(R.string.upcoming, upcomingMovieFromDb.movies)
                    )
                }
            )
                .applyVisibilityProgressBar(feedFragmentBinding.progressFeed as ProgressBar)
                .subscribe({
                    createCardMovies(it)
                    getMoviesFromApi()
                }, { error ->
                    Timber.d("Error", error.message)
                })
        compositeDisposable.add(disposable)
    }

    @SuppressLint("TimberArgCount")
    private fun getMoviesFromApi() {
        val popularMovie = popularMoviesRemoteUseCase.getMovies()
        val nowPlayingMovie = nowPlayingMoviesRemoteUseCase.getMovies()
        val upcomingMovie = upcomingMoviesRemoteUseCase.getMovies()

        val disposable = Single.zip(popularMovie, nowPlayingMovie, upcomingMovie,
            { popularMovie, nowPlayingMovie, upcomingMovie ->
                    linkedMapOf(
                        MovieCategory.POPULAR to ResultFeedMovies(R.string.popular, popularMovie),
                        MovieCategory.NOWPLAYING to ResultFeedMovies(R.string.recommended, nowPlayingMovie),
                        MovieCategory.UPCOMING to ResultFeedMovies(R.string.upcoming, upcomingMovie)
                    )
                }
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { progress_feed.visibility = View.VISIBLE }
                .doFinally { progress_feed.visibility = View.INVISIBLE }
                .subscribe({
                    createCardMovies(it)
                   val movies =  it.flatMap {
                        it.value.movies.map {
                            Mapper().toValueObjectForDbMovie(it)
                        }
                    }
                    val moviesByCat = it.flatMap {
                        it.value.movies.map {m->
                            MovieAndCategoryCrossRef(m.id, getString(it.value.titleRes))
                        }
                    }.distinct()
                    dbMovieRepository.setMovies(movies)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            dbMovieRepository.saveMoviesByCategories(moviesByCat)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({}, {
                                    println(it.message)
                                })
                        }, {
                            println("lalala ${it.message}")
                        })

                }, { error ->
                    Timber.d("Error", error.message)
                })
        compositeDisposable.add(disposable)
    }

    private fun <T>createCardMovies(movies: Map<MovieCategory, ResultFeedMovies<T>>) {
        adapter.clear()
        for (movie in movies) {
            val listMovieItem = movie.value.movies.map {
                MovieItem(it as Movie) { movie ->
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
