package ru.androidschool.intensiv.presentation.tvshows

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.tv_shows_fragment.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.repository.remote_repository.TvShowsRemoteRepository
import ru.androidschool.intensiv.data.vo.Movie
import ru.androidschool.intensiv.databinding.TvShowsFragmentBinding
import ru.androidschool.intensiv.domain.usecase.remote_use_case.TvShowsRemoteUseCase

class TvShowsFragment : Fragment(R.layout.tv_shows_fragment), TvShowView {

    private lateinit var tvShowsFragmentBinding: TvShowsFragmentBinding

    private val presenter: TvShowPresenter by lazy {
        TvShowPresenter(TvShowsRemoteUseCase(TvShowsRemoteRepository()))
    }

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    @SuppressLint("TimberArgCount")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvShowsFragmentBinding = TvShowsFragmentBinding.bind(view)
        presenter.attachView(this)
        presenter.getTvShows()
    }

    override fun onStop() {
        super.onStop()
        adapter.clear()
        presenter.clear()
    }

    override fun showTvShows(tvShows: List<Movie>) {
        val list = tvShows.map {
            TvShowItem(
                it
            ) { tvShow -> }
        }
        tvShowsFragmentBinding.tvShowRecyclerView.adapter =
            adapter.apply {
                addAll(list)
            }
    }

    override fun showProgressBar() {
        progress_tv_shows.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progress_tv_shows.visibility = View.INVISIBLE
    }
}
