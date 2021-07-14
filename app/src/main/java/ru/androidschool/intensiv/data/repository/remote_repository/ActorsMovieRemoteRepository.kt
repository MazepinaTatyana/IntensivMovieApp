package ru.androidschool.intensiv.data.repository.remote_repository

import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.domain.repository.ActorsMovieRepository
import ru.androidschool.intensiv.data.dto.details_movie.ActorResponseDto
import ru.androidschool.intensiv.data.network.MovieApiInterface

class ActorsMovieRemoteRepository : ActorsMovieRepository, KoinComponent {

    private val movieApiClient: MovieApiInterface by inject()

    override fun getActorsMovie(movieId: Int): Single<ActorResponseDto> {
        return movieApiClient.getMovieActors(movieId)
    }
}
