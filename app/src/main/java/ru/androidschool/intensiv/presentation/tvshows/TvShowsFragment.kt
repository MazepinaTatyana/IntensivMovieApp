package ru.androidschool.intensiv.presentation.tvshows

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.tv_shows_fragment.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.repository.remote_repository.TvShowsRemoteRepository
import ru.androidschool.intensiv.databinding.TvShowsFragmentBinding
import ru.androidschool.intensiv.domain.usecase.TvShowsRemoteUseCase
import ru.androidschool.intensiv.domain.usecase.extensions.applyVisibilityProgressBar
import timber.log.Timber

class TvShowsFragment : Fragment(R.layout.tv_shows_fragment) {

    private lateinit var tvShowsFragmentBinding: TvShowsFragmentBinding
    private val twShowsRemoteUseCase = TvShowsRemoteUseCase(TvShowsRemoteRepository())
    private lateinit var disposable: Disposable
    private var compositeDisposable = CompositeDisposable()

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    @SuppressLint("TimberArgCount")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvShowsFragmentBinding = TvShowsFragmentBinding.bind(view)
        disposable = twShowsRemoteUseCase.getTVShows()
            .applyVisibilityProgressBar(progress_tv_shows)
            .subscribe({
                val list = it.map {
                    TvShowItem(
                        it
                    ) { tvShow -> }
                }
                tvShowsFragmentBinding.tvShowRecyclerView.adapter =
                    adapter.apply {
                        addAll(list)
                    }
            }, { error ->
                Timber.d("Error tvShow", error.message)
            })
        compositeDisposable.add(disposable)
    }

    override fun onStop() {
        super.onStop()
        adapter.clear()
        compositeDisposable.clear()
    }
}
