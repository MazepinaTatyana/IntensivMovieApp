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
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.android.synthetic.main.feed_header.*
import kotlinx.android.synthetic.main.search_toolbar.view.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.Mapper
import ru.androidschool.intensiv.data.movies.DBMovieRepository
import ru.androidschool.intensiv.data.movies.RemoteMovieRepository
import ru.androidschool.intensiv.model.db_movie_model.Category
import ru.androidschool.intensiv.model.db_movie_model.CategoryWithMovies
import ru.androidschool.intensiv.model.db_movie_model.Movie
import ru.androidschool.intensiv.model.db_movie_model.MovieAndCategoryCrossRef
import ru.androidschool.intensiv.model.movie_model.ApiResponse
import ru.androidschool.intensiv.model.movie_model.ResultApi
import ru.androidschool.intensiv.model.movie_model.ResultFeedMovies
import ru.androidschool.intensiv.ui.afterTextChanged
import timber.log.Timber

class FeedFragment : Fragment(R.layout.feed_fragment) {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }
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

        dbMovieRepository = DBMovieRepository(requireContext())
        initCategories()
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
        categories.add(Category(categoryId = R.string.popular))
        categories.add(Category(categoryId = R.string.upcoming))
        categories.add(Category(categoryId = R.string.recommended))
        disposable = dbMovieRepository.setCategories(categories)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}
            , {})
    }

    @SuppressLint("TimberArgCount")
    private fun getMoviesFromDb() {

        val popularMovieFromDb = dbMovieRepository.getCategoryWithMoviesById(R.string.popular)
        val nowPlayingMovieFromDb = dbMovieRepository.getCategoryWithMoviesById(R.string.recommended)
        val upcomingMovieFromDb = dbMovieRepository.getCategoryWithMoviesById(R.string.upcoming)

        val disposable =
            Single.zip( popularMovieFromDb, nowPlayingMovieFromDb, upcomingMovieFromDb,
                Function3<CategoryWithMovies, CategoryWithMovies, CategoryWithMovies, Map<MovieCategory,ResultFeedMovies<Movie>>> { popularMovieFromDb, nowPlayingMovieFromDb, upcomingMovieFromDb ->
                    linkedMapOf(MovieCategory.POPULAR to ResultFeedMovies(popularMovieFromDb.category.categoryId, popularMovieFromDb.movies),
                        MovieCategory.NOWPLAYING to ResultFeedMovies(R.string.recommended, nowPlayingMovieFromDb.movies),
                        MovieCategory.UPCOMING to ResultFeedMovies(R.string.upcoming, upcomingMovieFromDb.movies)
                    )
                }
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { progress_feed.visibility = View.VISIBLE }
                .doFinally { progress_feed.visibility = View.INVISIBLE }
                .subscribe({
                    createCardMovies(it)
                    getMoviesFromApi()
//                    val movies = it.flatMap{
//                        it.value.movies
////                            .map {
////                            mapper.convertMovie(it)
////                        }
//                    }
//                    val moviesByCat = it.flatMap {
//                        it.value.movies.map {m->
//                            MovieAndCategoryCrossRef(it.value.titleRes,
//                                m.id )
//                        }
//                    }
                }, { error ->
                    Timber.d("Error", error.message)
                })
        compositeDisposable.add(disposable)
    }

    @SuppressLint("TimberArgCount")
    private fun getMoviesFromApi() {
        val popularMovie = remoteMovieRepository.getPopularMovies()
        val nowPlayingMovie = remoteMovieRepository.getNowPlayingMovies()
        val upcomingMovie = remoteMovieRepository.getUpcomingMovies()

        val disposable = Observable.zip(popularMovie, nowPlayingMovie, upcomingMovie,
                Function3<ApiResponse, ApiResponse, ApiResponse, Map<MovieCategory, ResultFeedMovies<ResultApi>>> { popularMovie, nowPlayingMovie, upcomingMovie ->
                    linkedMapOf(
                        MovieCategory.POPULAR to ResultFeedMovies(R.string.popular, popularMovie.results),
                        MovieCategory.NOWPLAYING to ResultFeedMovies(R.string.recommended, nowPlayingMovie.results),
                        MovieCategory.UPCOMING to ResultFeedMovies(R.string.upcoming, upcomingMovie.results)
                    )
                }
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { progress_feed.visibility = View.VISIBLE }
                .doFinally { progress_feed.visibility = View.INVISIBLE }
                .subscribe({
                    createCardMovies(it)
                    val mapper = Mapper();
                    val movies = it.flatMap{map ->
                        map.value.movies.map {result ->
                            mapper.convertMovie(result);
                        }
                    }
                    val moviesByCat = it.flatMap {
                        it.value.movies.map {m->
                            MovieAndCategoryCrossRef(it.value.titleRes,
                                m.id )
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
                        }, {})

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
