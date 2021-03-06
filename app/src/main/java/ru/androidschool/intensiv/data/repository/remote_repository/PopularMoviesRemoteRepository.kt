package ru.androidschool.intensiv.data.repository.remote_repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.MapperRemoteToVo
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.data.vo.Movie
import ru.androidschool.intensiv.domain.repository.MovieRepository

class PopularMoviesRemoteRepository : MovieRepository {

    override fun getMovies(): Single<List<Movie>> {
        return MovieApiClient.movieApiClient.getPopularMovies()
            .map { MapperRemoteToVo.convertToListMovie(it) }
    }
}
