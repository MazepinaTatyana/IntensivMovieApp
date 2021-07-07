package ru.androidschool.intensiv.domain.usecase

import io.reactivex.Single
import ru.androidschool.intensiv.data.vo.DetailsMovie
import ru.androidschool.intensiv.data.vo.Movie
import ru.androidschool.intensiv.domain.repository.DetailsMovieRepository
import ru.androidschool.intensiv.domain.usecase.extensions.applySchedulers

class DetailsMovieRemoteUseCase(private val repository: DetailsMovieRepository) {

    fun getDetailsMovieById(movieId: Int): Single<DetailsMovie> {
        return repository.getDetailsMovieById(movieId)
            .applySchedulers()
    }
}
