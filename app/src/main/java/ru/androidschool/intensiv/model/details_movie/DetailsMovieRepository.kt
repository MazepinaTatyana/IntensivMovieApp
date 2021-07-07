package ru.androidschool.intensiv.model.details_movie

import io.reactivex.Observable
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.data.vo.DetailsMovie
import ru.androidschool.intensiv.presentation.movie_details.ActorResponse

object DetailsMovieRepository {

    fun getDetailsMovieById(movieId: Int): Observable<DetailsMovie> = MovieApiClient.movieApiClient.getDetailsMovieById(movieId)

    fun getActorsMovie(movieId: Int): Observable<ActorResponse> = MovieApiClient.movieApiClient.getMovieActors(movieId)
}
