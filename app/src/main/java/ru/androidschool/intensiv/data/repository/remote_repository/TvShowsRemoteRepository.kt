package ru.androidschool.intensiv.data.repository.remote_repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.mappers.Mapper
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.data.vo.Movie
import ru.androidschool.intensiv.domain.repository.TvShowsRepository

class TvShowsRemoteRepository: TvShowsRepository {
    override fun getTVShows(): Single<List<Movie>> {
        return MovieApiClient.movieApiClient.getTvShows()
            .map { Mapper().convertToListMovie(it) }
    }
}