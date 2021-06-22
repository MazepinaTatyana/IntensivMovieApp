package ru.androidschool.intensiv.data.details_movie

import io.reactivex.Observable
import ru.androidschool.intensiv.model.details_movie_model.DetailsMovieModel
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.ui.movie_details.ActorResponse

object DetailsMovieRepository {

    fun getDetailsMovieById(movieId: Int): Observable<DetailsMovieModel> = MovieApiClient.movieApiClient.getDetailsMovieById(movieId)

    fun getActorsMovie(movieId: Int): Observable<ActorResponse> = MovieApiClient.movieApiClient.getMovieActors(movieId)
}
