package ru.androidschool.intensiv.data.tv_shows

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.model.movie_model.ResultApi
import ru.androidschool.intensiv.network.MovieApiClient

object TVShowsRepository {

    val compositeDisposable = CompositeDisposable()
    var tvShowList = MutableLiveData<List<ResultApi>>()
    var errors = MutableLiveData<Throwable>()

    fun getTVShows() {
        compositeDisposable.add(MovieApiClient.movieApiClient.getTvShows()
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                tvShowList.postValue(it.results)
            }, { error -> errors.postValue(error)})
        )
    }
}
