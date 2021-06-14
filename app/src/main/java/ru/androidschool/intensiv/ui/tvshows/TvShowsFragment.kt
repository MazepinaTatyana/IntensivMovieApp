package ru.androidschool.intensiv.ui.tvshows

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.databinding.TvShowsFragmentBinding
import ru.androidschool.intensiv.extensions.response
import ru.androidschool.intensiv.network.MovieApiClient
import timber.log.Timber

class TvShowsFragment : Fragment(R.layout.tv_shows_fragment) {

    private lateinit var tvShowsFragmentBinding: TvShowsFragmentBinding

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    @SuppressLint("TimberArgCount")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvShowsFragmentBinding = TvShowsFragmentBinding.bind(view)

        MovieApiClient.movieApiClient.getTvShows().response {
            onFailure = { error ->
                Timber.e("error tvShows", error?.message.toString())
            }
            onResponse = { respounse ->
                val tvShowList = respounse.body()?.results?.map {
                        TvShowItem(
                            it
                        ) { tvShow -> }
                    }

                tvShowsFragmentBinding.tvShowRecyclerView.adapter =
                    adapter.apply {
                        if (tvShowList != null) {
                            addAll(tvShowList)
                        }
                    }
            }
        }
    }
}
