package ru.androidschool.intensiv.data.tv_shows

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.model.movie_model.ResultApi
import ru.androidschool.intensiv.network.MovieApiClient

object TVShowsRepository {

    val compositeDisposable = CompositeDisposable()
    lateinit var disposable: Disposable
    var tvShowList = MutableLiveData<List<ResultApi>>()
    var errors = MutableLiveData<Throwable>()

    fun getTVShows() {
        disposable = MovieApiClient.movieApiClient.getTvShows()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                tvShowList.postValue(it.results)
            }, {
                    error -> errors.postValue(error)
            })
        compositeDisposable.add(disposable)
    }
    fun clear() {
        disposable.dispose()
        compositeDisposable.clear()
    }
}
