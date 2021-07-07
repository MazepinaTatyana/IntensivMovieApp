package ru.androidschool.intensiv.data.repository.remote_repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.domain.repository.ActorsMovieRepository
import ru.androidschool.intensiv.presentation.movie_details.ActorResponse

class ActorsMovieRemoteRepository: ActorsMovieRepository {
    override fun getActorsMovie(movieId: Int): Single<ActorResponse> {
       return MovieApiClient.movieApiClient.getMovieActors(movieId)
    }
}
