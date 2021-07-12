package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.dto.movie_dto.MovieDto
import ru.androidschool.intensiv.data.dto.movie_dto.MoviesApiResponseDto
import ru.androidschool.intensiv.data.vo.Movie

object MapperRemoteToVo :
    BaseMapper<MovieDto> {

    override fun toViewObject(dto: MovieDto): Movie {
        return Movie(
            id = dto.id,
            originalTitle = dto.originalTitle,
            overview = dto.overview?.let { it } ?: "",
            popularity = dto.popularity,
            posterPath = dto.posterPath?.let { it } ?: "",
            releaseDate = dto.releaseDate,
            title = dto.title,
            voteAverage = dto.voteAverage
        )
    }

    fun convertToListMovie(dto: MoviesApiResponseDto): List<Movie> {
        return dto.results.map { convertToMovie(it) }
    }

    private fun convertToMovie(dto: MovieDto): Movie {
        return Movie(
            id = dto.id,
            originalTitle = dto.originalTitle,
            overview = dto.overview?.let { it } ?: "",
            popularity = dto.popularity,
            posterPath = dto.posterPath?.let { it } ?: "",
            releaseDate = dto.releaseDate,
            title = dto.title?.let { it } ?: "",
            voteAverage = dto.voteAverage,
            name = dto.name?.let { it } ?: ""
        )
    }
}
