package ru.androidschool.intensiv.ui.tvshows

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.reactivex.Observable
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.tv_shows.TVShowsRepository
import ru.androidschool.intensiv.databinding.TvShowsFragmentBinding
import ru.androidschool.intensiv.extensions.response
import ru.androidschool.intensiv.model.movie_model.ApiResponse
import ru.androidschool.intensiv.network.MovieApiClient
import timber.log.Timber

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

        tvShowList.observe(viewLifecycleOwner, Observer {
            val list = it.map {
                TvShowItem(
                    it
                ) { tvShow -> }
            }
            tvShowsFragmentBinding.tvShowRecyclerView.adapter =
                adapter.apply {
                    addAll(list)
                }
        })
    }
}
