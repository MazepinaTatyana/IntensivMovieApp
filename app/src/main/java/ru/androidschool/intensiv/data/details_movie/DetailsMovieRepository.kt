package ru.androidschool.intensiv.data.details_movie

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.model.details_movie_model.DetailsMovieModel
import ru.androidschool.intensiv.model.movie_model.ResultApi
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.ui.movie_details.Actor
import ru.androidschool.intensiv.ui.movie_details.ActorResponse

object DetailsMovieRepository {

    var movieDetails = MutableLiveData<DetailsMovieModel>()
    var actors = MutableLiveData<ActorResponse>()
    val compositeDisposable = CompositeDisposable()
    var errors = MutableLiveData<Throwable>()

    fun getDetailsMovieById(movieId: Int) {
       compositeDisposable.add(MovieApiClient.movieApiClient.getDetailsMovieById(movieId)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                movieDetails.postValue(it)
            }, {
                errors.postValue(it)
            })
       )
    }

    fun getActorsMovie(movieId: Int) {
        compositeDisposable.add(MovieApiClient.movieApiClient.getMovieActors(movieId)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                actors.postValue(it)
            }, {
                errors.postValue(it)
            })
        )
    }
}
