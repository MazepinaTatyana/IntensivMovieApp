package ru.androidschool.intensiv.domain.usecase

import io.reactivex.Single
import ru.androidschool.intensiv.domain.repository.ActorsMovieRepository
import ru.androidschool.intensiv.domain.usecase.extensions.applySchedulers
import ru.androidschool.intensiv.data.dto.details_movie_dto.ActorResponseDto

class ActorsMovieRemoteUseCase(private val repository: ActorsMovieRepository) {

    fun getActorsMovie(movieId: Int): Single<ActorResponseDto> {
        return repository.getActorsMovie(movieId)
            .applySchedulers()
    }
}
