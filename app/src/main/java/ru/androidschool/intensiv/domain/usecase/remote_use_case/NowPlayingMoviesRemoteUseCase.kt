package ru.androidschool.intensiv.domain.usecase.remote_use_case

import io.reactivex.Single
import ru.androidschool.intensiv.data.dto.movie_dto.MoviesApiResponseDto
import ru.androidschool.intensiv.domain.repository.MovieRepository
import ru.androidschool.intensiv.domain.usecase.extensions.applySchedulers

class NowPlayingMoviesRemoteUseCase(private val repository: MovieRepository) {

    fun getMovies(): Single<MoviesApiResponseDto> {
        return repository.getMovies()
            .applySchedulers()
    }
}
