package ru.androidschool.intensiv.ui.tvshows

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.tv_shows.TVShowsRepository
import ru.androidschool.intensiv.databinding.TvShowsFragmentBinding

class TvShowsFragment : Fragment(R.layout.tv_shows_fragment) {

    private lateinit var tvShowsFragmentBinding: TvShowsFragmentBinding
    private var tvShowsRepository = TVShowsRepository
    private var tvShowList = tvShowsRepository.tvShowList

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    @SuppressLint("TimberArgCount")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvShowsFragmentBinding = TvShowsFragmentBinding.bind(view)
        tvShowsRepository.getTVShows()
        var isAdded = false
        tvShowList.observe(viewLifecycleOwner, Observer {
            val list = it.map {
                TvShowItem(
                    it
                ) { tvShow -> }
            }
            tvShowsFragmentBinding.tvShowRecyclerView.adapter =
                adapter.apply {
                    if (!isAdded) {
                        addAll(list)
                        isAdded = true
                    }
                }
        })
    }

    override fun onStop() {
        super.onStop()
        adapter.clear()
        tvShowsRepository.clear()
    }
}
