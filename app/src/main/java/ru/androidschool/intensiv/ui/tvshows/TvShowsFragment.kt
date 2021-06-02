package ru.androidschool.intensiv.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_watchlist.*
import kotlinx.android.synthetic.main.tv_shows_fragment.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.MockRepository
import ru.androidschool.intensiv.data.MockTVShowsRepository
import ru.androidschool.intensiv.ui.watchlist.MoviePreviewItem

class TvShowsFragment : Fragment(R.layout.tv_shows_fragment) {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_show_recycler_view.layoutManager = LinearLayoutManager(requireContext())
        tv_show_recycler_view.adapter = adapter.apply { addAll(listOf()) }

        val tvShowList =
            MockTVShowsRepository.getTVShows().map {
                TvShowItem(
                    it
                ) { tvShow -> }
            }.toList()

        tv_show_recycler_view.adapter = adapter.apply { addAll(tvShowList) }
    }

}
