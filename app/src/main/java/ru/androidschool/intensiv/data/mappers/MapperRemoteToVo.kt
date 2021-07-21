package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.dto.movie_dto.MovieDto
import ru.androidschool.intensiv.data.dto.movie_dto.MoviesApiResponseDto
import ru.androidschool.intensiv.data.vo.Movie

object MapperRemoteToVo :
    BaseMapper<MovieDto> {

    override fun toViewObject(dto: MovieDto): Movie {
        return Movie(
            id = dto.id?.let { it } ?: 0,
            originalTitle = dto.originalTitle?.let { it } ?: "",
            overview = dto.overview?.let { it } ?: "",
            popularity = dto.popularity?.let { it } ?: 0.0,
            posterPath = dto.posterPath?.let { it } ?: "",
            releaseDate = dto.releaseDate?.let { it } ?: "",
            title = dto.title?.let { it } ?: "",
            voteAverage = dto.voteAverage?.let { it } ?: 0.0,
            name = dto.name?.let { it } ?: ""
        )
    }

    fun convertToListMovie(dto: MoviesApiResponseDto): List<Movie> {
        return dto.results?.map { toViewObject(it) } ?: listOf()
    }
}
