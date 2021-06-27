package ru.androidschool.intensiv.ui.watchlist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.feed_fragment.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.movies.MovieDto
import ru.androidschool.intensiv.database.MovieDatabase
import ru.androidschool.intensiv.databinding.FragmentWatchlistBinding
import ru.androidschool.intensiv.model.db_movie_model.Movie
import ru.androidschool.intensiv.ui.feed.MainCardContainer
import timber.log.Timber

class WatchlistFragment : Fragment(R.layout.fragment_watchlist) {

    private lateinit var watchlistBinding: FragmentWatchlistBinding
    private var moviesList = listOf<MoviePreviewItem>()
    private lateinit var disposable: Disposable
    private var compositeDisposable = CompositeDisposable()
    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    @SuppressLint("TimberArgCount")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        watchlistBinding = FragmentWatchlistBinding.bind(view)
        watchlistBinding.moviesRecyclerView.layoutManager = GridLayoutManager(context, 4)

        disposable = MovieDatabase.getInstance(requireContext()).getMovieDao().getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movies ->
                moviesList = movies.map {
                    MoviePreviewItem(
                        it
                    ) { movie -> }
                }.toList()

                watchlistBinding.moviesRecyclerView.adapter = adapter.apply {
                    addAll(moviesList)

                }

            }, {
                Timber.e("error db", it.message)
            })
    }
}
