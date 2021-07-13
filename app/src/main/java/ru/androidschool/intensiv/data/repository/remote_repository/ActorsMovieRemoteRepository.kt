package ru.androidschool.intensiv.data.repository.remote_repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.domain.repository.ActorsMovieRepository
import ru.androidschool.intensiv.data.dto.details_movie.ActorResponseDto

class ActorsMovieRemoteRepository : ActorsMovieRepository {
    override fun getActorsMovie(movieId: Int): Single<ActorResponseDto> {
        return MovieApiClient.movieApiClient.getMovieActors(movieId)
    }
}
