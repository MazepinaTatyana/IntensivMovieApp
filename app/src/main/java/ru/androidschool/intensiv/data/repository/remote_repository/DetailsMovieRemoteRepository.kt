package ru.androidschool.intensiv.data.repository.remote_repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.Mapper
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.data.vo.Movie
import ru.androidschool.intensiv.domain.repository.DetailsMovieRepository

class DetailsMovieRemoteRepository: DetailsMovieRepository {

    override fun getDetailsMovieById(movieId: Int): Single<Movie> {
        return MovieApiClient.movieApiClient.getDetailsMovieById(movieId)
            .map { Mapper().convertMovie(it) }
    }
}
