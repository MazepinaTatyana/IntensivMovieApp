package ru.androidschool.intensiv.data.repository.remote_repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.dto.movie_dto.MoviesApiResponseDto
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.domain.repository.MovieRepository

class PopularMoviesRemoteRepository : MovieRepository {

    override fun getMovies(): Single<MoviesApiResponseDto> {
        return MovieApiClient.movieApiClient.getPopularMovies()
    }
}