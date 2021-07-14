package ru.androidschool.intensiv.domain.usecase.remote_use_case

import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.androidschool.intensiv.data.vo.DetailsMovie
import ru.androidschool.intensiv.domain.repository.DetailsMovieRepository
import ru.androidschool.intensiv.domain.usecase.extensions.applySchedulers

class DetailsMovieRemoteUseCase(private val repository: DetailsMovieRepository) {

    fun getDetailsMovieById(movieId: Int): Single<DetailsMovie> {
        return repository.getDetailsMovieById(movieId)
            .applySchedulers()
    }
}
