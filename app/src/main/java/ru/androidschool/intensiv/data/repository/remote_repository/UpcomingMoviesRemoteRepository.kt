package ru.androidschool.intensiv.data.repository.remote_repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.Mapper
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.data.vo.Movie
import ru.androidschool.intensiv.domain.repository.MovieRepository

class UpcomingMoviesRemoteRepository: MovieRepository {
    override fun getMovies(): Single<List<Movie>> {
        return MovieApiClient.movieApiClient.getUpcomingMovies()
            .map { Mapper().convertToListMovie(it) }
    }
}
