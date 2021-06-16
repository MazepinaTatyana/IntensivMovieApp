package ru.androidschool.intensiv.data.movies

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.http.GET
import retrofit2.http.Query
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.model.movie_model.ApiResponse
import ru.androidschool.intensiv.model.movie_model.ResultApi
import ru.androidschool.intensiv.network.MovieApiClient

object MovieRepository {

    val compositeDisposable = CompositeDisposable()
    var movieList = MutableLiveData<List<ResultApi>>()
    var errors = MutableLiveData<Throwable>()

    fun getNowPlayingMovies() {
        compositeDisposable.add(
            MovieApiClient.movieApiClient.getNowPlayingMovies()
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                movieList.postValue(it.results)
            }, { error -> errors.postValue(error)})
        )
    }

    fun getPopularMovies() {
        compositeDisposable.add(
            MovieApiClient.movieApiClient.getPopularMovies()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    movieList.postValue(it.results)
                }, { error -> errors.postValue(error)})
        )
    }

    fun getUpcomingMovies() {
        compositeDisposable.add(
            MovieApiClient.movieApiClient.getUpcomingMovies()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    movieList.postValue(it.results)
                }, { error -> errors.postValue(error)})
        )
    }
}
