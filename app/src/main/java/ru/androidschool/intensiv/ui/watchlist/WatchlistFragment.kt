package ru.androidschool.intensiv.ui.watchlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_watchlist.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.movies.MockRepository
import ru.androidschool.intensiv.databinding.FragmentWatchlistBinding

class WatchlistFragment : Fragment(R.layout.fragment_watchlist) {

    private lateinit var watchlistBinding: FragmentWatchlistBinding
    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        watchlistBinding = FragmentWatchlistBinding.bind(view)
        watchlistBinding.moviesRecyclerView.layoutManager = GridLayoutManager(context, 4)

        val moviesList =
            MockRepository.getMovies().map {
                MoviePreviewItem(
                    it
                ) { movie -> }
            }.toList()

        watchlistBinding.moviesRecyclerView.adapter = adapter.apply { addAll(moviesList) }
    }
}
