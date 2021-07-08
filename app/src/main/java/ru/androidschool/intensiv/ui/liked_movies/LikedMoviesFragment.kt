package ru.androidschool.intensiv.ui.liked_movies

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.movies.DBMovieRepository
import ru.androidschool.intensiv.data.movies.MovieVo
import ru.androidschool.intensiv.databinding.FragmentLikedMoviesBinding
import ru.androidschool.intensiv.model.db_movie_model.Movie
import ru.androidschool.intensiv.ui.watchlist.MoviePreviewItem
import timber.log.Timber

class LikedMoviesFragment : Fragment(R.layout.fragment_liked_movies) {

    private lateinit var likedMoviesBinding: FragmentLikedMoviesBinding
    var moviesList = listOf<MoviePreviewItem>()
    private lateinit var disposable: Disposable
    private lateinit var dbRepository: DBMovieRepository
    private var compositeDisposable = CompositeDisposable()
    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }
    val countMovies = MutableLiveData<Int>()

    @SuppressLint("TimberArgCount")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        likedMoviesBinding = FragmentLikedMoviesBinding.bind(view)
        dbRepository = DBMovieRepository(requireContext())
        val likedMoviesRecycler = likedMoviesBinding.likedMovies.root
        likedMoviesRecycler.layoutManager = GridLayoutManager(context, 4)

        disposable = dbRepository.getFavouriteMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movies ->
                var movie: Movie
                val movies = movies.map {
                    val favouriteMovies = arrayListOf<Movie>()
                    dbRepository.getMovieById(it)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ it ->
                            moviesList = listOf(MoviePreviewItem(
                                it
                            ) { movie -> })
                        }, {

                        })
//                    setMoviePreviewItem(movie)
                }

                likedMoviesRecycler.adapter = adapter.apply {
                    addAll(moviesList)
                }
                countMovies.postValue(movies.size)

            }, {
                Timber.e("error db", it.message)
            })
        compositeDisposable.add(disposable)
    }

    private fun setMoviePreviewItem(movie: Movie) = MoviePreviewItem(
        movie
    ) { movie -> }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
