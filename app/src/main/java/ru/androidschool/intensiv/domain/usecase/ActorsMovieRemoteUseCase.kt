package ru.androidschool.intensiv.domain.usecase

import io.reactivex.Single
import ru.androidschool.intensiv.domain.repository.ActorsMovieRepository
import ru.androidschool.intensiv.domain.usecase.extensions.applySchedulers
import ru.androidschool.intensiv.presentation.movie_details.ActorResponse

class ActorsMovieRemoteUseCase(private val repository: ActorsMovieRepository) {

    fun getActorsMovie(movieId: Int): Single<ActorResponse> {
        return repository.getActorsMovie(movieId)
            .applySchedulers()
    }
}
