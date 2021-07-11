package ru.androidschool.intensiv.data.details_movie

import io.reactivex.Single
import ru.androidschool.intensiv.model.details_movie_model.DetailsMovieModel
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.ui.movie_details.ActorResponse

object DetailsMovieRepository {

    fun getDetailsMovieById(movieId: Int): Single<DetailsMovieModel> = MovieApiClient.movieApiClient.getDetailsMovieById(movieId)

    fun getActorsMovie(movieId: Int): Single<ActorResponse> = MovieApiClient.movieApiClient.getMovieActors(movieId)
}
