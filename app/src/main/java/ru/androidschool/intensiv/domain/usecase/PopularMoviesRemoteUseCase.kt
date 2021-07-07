package ru.androidschool.intensiv.domain.usecase

import io.reactivex.Single
import ru.androidschool.intensiv.data.vo.Movie
import ru.androidschool.intensiv.domain.repository.MovieRepository
import ru.androidschool.intensiv.domain.usecase.extensions.applySchedulers

class PopularMoviesRemoteUseCase(private val repository: MovieRepository) {

    fun getMovies(): Single<List<Movie>> {
        return repository.getMovies()
            .applySchedulers()
    }
}