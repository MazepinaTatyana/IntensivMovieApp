package ru.androidschool.intensiv.domain.usecase

import io.reactivex.Single
import ru.androidschool.intensiv.data.dto.movie_dto.MoviesApiResponseDto
import ru.androidschool.intensiv.domain.repository.MovieRepository
import ru.androidschool.intensiv.domain.usecase.extensions.applySchedulers

class PopularMoviesRemoteUseCase(private val repository: MovieRepository) {

    fun getMovies(): Single<MoviesApiResponseDto> {
        return repository.getMovies()
            .applySchedulers()
    }
}