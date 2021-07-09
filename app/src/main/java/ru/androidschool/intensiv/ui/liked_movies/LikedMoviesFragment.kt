package ru.androidschool.intensiv.ui.liked_movies

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.movies.DBMovieRepository
import ru.androidschool.intensiv.databinding.FragmentLikedMoviesBinding
import ru.androidschool.intensiv.extensions.applySchedulers
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
    private val countMovies = MutableLiveData<Int>()

    @SuppressLint("TimberArgCount")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        likedMoviesBinding = FragmentLikedMoviesBinding.bind(view)
        dbRepository = DBMovieRepository(requireContext())
        val likedMoviesRecycler = likedMoviesBinding.likedMovies.root
        likedMoviesRecycler.layoutManager = GridLayoutManager(context, 4)

        disposable = dbRepository.getFavouriteMovies()
            .applySchedulers()
            .subscribe({ movies ->
                moviesList = movies.map {
                    MoviePreviewItem(
                        it.movie
                    ) { movie -> }
                }.toList()

                likedMoviesRecycler.adapter = adapter.apply {
                    addAll(moviesList)
                }
                countMovies.postValue(movies.size)
            }, {
                Timber.e("error db", it.message)
            })
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
