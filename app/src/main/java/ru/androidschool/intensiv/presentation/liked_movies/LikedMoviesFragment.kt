package ru.androidschool.intensiv.presentation.liked_movies

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
import ru.androidschool.intensiv.data.db.database.MovieDatabase
import ru.androidschool.intensiv.data.repository.db_repository.DbFavouriteMovieRepository
import ru.androidschool.intensiv.databinding.FragmentLikedMoviesBinding
import ru.androidschool.intensiv.domain.usecase.db_use_case.DbFavouriteMovieUseCase
import ru.androidschool.intensiv.domain.usecase.extensions.applySchedulers
import ru.androidschool.intensiv.presentation.watchlist.MoviePreviewItem
import timber.log.Timber

class LikedMoviesFragment : Fragment(R.layout.fragment_liked_movies) {

    private lateinit var likedMoviesBinding: FragmentLikedMoviesBinding
    var moviesList = listOf<MoviePreviewItem>()
    private lateinit var disposable: Disposable
    private lateinit var dbFavouriteRepository: DbFavouriteMovieRepository
    private lateinit var dbFavouriteUseCase: DbFavouriteMovieUseCase
    private var compositeDisposable = CompositeDisposable()
    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }
    val countMovies = MutableLiveData<Int>()

    @SuppressLint("TimberArgCount")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        likedMoviesBinding = FragmentLikedMoviesBinding.bind(view)
        val database = MovieDatabase.getInstance(requireContext())
        dbFavouriteRepository = DbFavouriteMovieRepository(database)
        dbFavouriteUseCase = DbFavouriteMovieUseCase(dbFavouriteRepository)
        val likedMoviesRecycler = likedMoviesBinding.likedMovies.root
        likedMoviesRecycler.layoutManager = GridLayoutManager(context, 4)

        disposable = dbFavouriteUseCase.getFavouriteMovies()
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