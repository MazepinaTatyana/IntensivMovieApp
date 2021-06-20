package ru.androidschool.intensiv.data.movies

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.model.movie_model.ResultApi
import ru.androidschool.intensiv.network.MovieApiClient

object MovieRepository {

    val compositeDisposable = CompositeDisposable()
    var popularMovieList = MutableLiveData<List<ResultApi>>()
    var nowPlayingMovieList = MutableLiveData<List<ResultApi>>()
    var upcomingMovieList = MutableLiveData<List<ResultApi>>()
    var errors = MutableLiveData<Throwable>()

    fun getNowPlayingMovies() {
        compositeDisposable.add(
            MovieApiClient.movieApiClient.getNowPlayingMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                nowPlayingMovieList.postValue(it.results)
            }, { error -> errors.postValue(error) })
        )
    }

    fun getPopularMovies() {
        compositeDisposable.add(
            MovieApiClient.movieApiClient.getPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    popularMovieList.value = it.results
                }, {
                        error -> errors.postValue(error)
                })
        )
    }

    fun getUpcomingMovies() {
        compositeDisposable.add(
            MovieApiClient.movieApiClient.getUpcomingMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    upcomingMovieList.value = (it.results)
                }, { error -> errors.postValue(error) })
        )
    }
    fun clear() {
        compositeDisposable.clear()
    }
}
