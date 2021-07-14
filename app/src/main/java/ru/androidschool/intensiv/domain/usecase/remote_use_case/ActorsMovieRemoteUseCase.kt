package ru.androidschool.intensiv.domain.usecase.remote_use_case

import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.androidschool.intensiv.domain.repository.ActorsMovieRepository
import ru.androidschool.intensiv.data.dto.details_movie.ActorResponseDto
import ru.androidschool.intensiv.domain.usecase.extensions.applySchedulers

class ActorsMovieRemoteUseCase(private val repository: ActorsMovieRepository) {

    fun getActorsMovie(movieId: Int): Single<ActorResponseDto> {
        return repository.getActorsMovie(movieId)
            .applySchedulers()
    }
}
