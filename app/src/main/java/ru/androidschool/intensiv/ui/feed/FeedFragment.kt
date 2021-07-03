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
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.android.synthetic.main.feed_header.*
import kotlinx.android.synthetic.main.search_toolbar.view.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.movies.DBMovieRepository
import ru.androidschool.intensiv.data.movies.RemoteMovieRepository
import ru.androidschool.intensiv.model.db_movie_model.Category
import ru.androidschool.intensiv.model.db_movie_model.Movie
import ru.androidschool.intensiv.model.movie_model.ApiResponse
import ru.androidschool.intensiv.model.movie_model.ResultApi
import ru.androidschool.intensiv.model.movie_model.ResultFeedMovies
import ru.androidschool.intensiv.model.movie_model.ResultFeedMoviesFromDb
import ru.androidschool.intensiv.ui.afterTextChanged
import timber.log.Timber

class FeedFragment : Fragment(R.layout.feed_fragment) {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }
    private val remoteMovieRepository = RemoteMovieRepository()
    private val dbMovieRepository = DBMovieRepository(requireContext())
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

        initCategories()


        val popularMovie = remoteMovieRepository.getPopularMovies()
        val nowPlayingMovie = remoteMovieRepository.getNowPlayingMovies()
        val upcomingMovie = remoteMovieRepository.getUpcomingMovies()

        val popularMovieFromDb = dbMovieRepository.getPopularMovies()
        val nowPlayingMovieFromDb = dbMovieRepository.getNowPlayingMovies()
        val upcomingMovieFromDb = dbMovieRepository.getUpcomingMovies()


        val disposable =  Observable.zip( popularMovieFromDb, nowPlayingMovieFromDb, upcomingMovieFromDb,
            Function3<List<Movie>, List<Movie>, List<Movie>, Map<MovieCategory,ResultFeedMoviesFromDb>> {popularMovieFromDb, nowPlayingMovieFromDb, upcomingMovieFromDb ->
                linkedMapOf(MovieCategory.POPULAR to ResultFeedMoviesFromDb(R.string.popular, popularMovieFromDb),
                    MovieCategory.NOWPLAYING to ResultFeedMoviesFromDb(R.string.recommended, nowPlayingMovieFromDb),
                    MovieCategory.UPCOMING to ResultFeedMoviesFromDb(R.string.upcoming, upcomingMovieFromDb)
                )
            }
        )
            .concatMap {
            Observable.zip(popularMovie, nowPlayingMovie, upcomingMovie,

            Function3<ApiResponse, ApiResponse, ApiResponse, Map<MovieCategory, ResultFeedMovies>> { popularMovie, nowPlayingMovie, upcomingMovie ->
                linkedMapOf(
                    MovieCategory.POPULAR to ResultFeedMovies(R.string.popular, popularMovie.results),
                    MovieCategory.NOWPLAYING to ResultFeedMovies(R.string.recommended, nowPlayingMovie.results),
                    MovieCategory.UPCOMING to ResultFeedMovies(R.string.upcoming, upcomingMovie.results)
                )
            }
        ) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progress_feed.visibility = View.VISIBLE }
            .doFinally { progress_feed.visibility = View.INVISIBLE }
            .subscribe({
                createCardMovies(it)
            }, { error ->
                Timber.d("Error", error.message)
            })
        compositeDisposable.add(disposable)
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

            })
    }

    private fun setCategories() {
        val categories = arrayListOf<Category>()
        categories.add(Category(title = getString(R.string.popular), movieCategory = MovieCategory.POPULAR))
        categories.add(Category(title = getString(R.string.upcoming), movieCategory = MovieCategory.UPCOMING))
        categories.add(Category(title = getString(R.string.recommended), movieCategory = MovieCategory.NOWPLAYING))
        disposable = dbMovieRepository.setCategories(categories)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}
            , {})
    }

    private fun createCardMovies(movies: Map<MovieCategory, ResultFeedMovies>) {
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

    private fun openMovieDetails(resultApi: ResultApi) {
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
