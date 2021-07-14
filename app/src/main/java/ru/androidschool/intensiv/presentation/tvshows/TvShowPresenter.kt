package ru.androidschool.intensiv.presentation.tvshows

import android.annotation.SuppressLint
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.androidschool.intensiv.domain.usecase.remote_use_case.TvShowsRemoteUseCase
import ru.androidschool.intensiv.presentation.base.BasePresenter
import timber.log.Timber

class TvShowPresenter(private val useCase: TvShowsRemoteUseCase) : BasePresenter<TvShowView>() {

    @SuppressLint("TimberArgCount")
    fun getTvShows() {
        val disposable = useCase.getTVShows()
            .doOnSubscribe { view?.showProgressBar() }
            .doFinally { view?.hideProgressBar() }
            .subscribe({
                view?.showTvShows(it)
            }, { error ->
                Timber.d("Error tvShow", error.message)
            })
        compositeDisposable.add(disposable)
    }
}