package ru.androidschool.intensiv.presentation.tvshows

import android.annotation.SuppressLint
import kotlinx.coroutines.coroutineScope
import ru.androidschool.intensiv.data.vo.Movie
import ru.androidschool.intensiv.domain.usecase.remote_use_case.TvShowsRemoteUseCase
import ru.androidschool.intensiv.presentation.base.BasePresenter
import timber.log.Timber

class TvShowPresenter(private val useCase: TvShowsRemoteUseCase) : BasePresenter<TvShowView>() {

    private var tvShows = listOf<Movie>()

    @SuppressLint("TimberArgCount")
    suspend fun getTvShows() {
        view?.showProgressBar()
        coroutineScope {
            try {
                tvShows = useCase.getTVShows()
                view?.showTvShows(tvShows)
                view?.hideProgressBar()
            } catch (error: Exception) {
                Timber.d("Error tvShow", error.message)
            }
        }
    }
}
