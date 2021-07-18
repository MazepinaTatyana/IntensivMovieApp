package ru.androidschool.intensiv.data.repository.remote_repository

import ru.androidschool.intensiv.data.dto.movie_dto.MovieDto
import ru.androidschool.intensiv.data.mappers.MapperRemoteToVo
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.data.vo.Movie
import ru.androidschool.intensiv.domain.repository.TvShowsRepository

class TvShowsRemoteRepository : TvShowsRepository {
    override suspend fun getTVShows(): List<Movie> {
        val tvShowsDto: List<MovieDto> = MovieApiClient
            .movieApiClient
            .getTvShows()
            .results?.let { it } ?: listOf()

        return tvShowsDto.map {
            MapperRemoteToVo.toViewObject(it)
        }
    }
}
