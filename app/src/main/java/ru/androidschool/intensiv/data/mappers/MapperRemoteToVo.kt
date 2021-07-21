package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.dto.movie_dto.MovieDto
import ru.androidschool.intensiv.data.dto.movie_dto.MoviesApiResponseDto
import ru.androidschool.intensiv.data.vo.Movie

object MapperRemoteToVo :
    BaseMapper<MovieDto> {

    override fun toViewObject(dto: MovieDto): Movie {
        return Movie(
            id = dto.id ?: 0,
            originalTitle = dto.originalTitle ?: "",
            overview = dto.overview ?: "",
            popularity = dto.popularity ?: 0.0,
            posterPath = dto.posterPath ?: "",
            releaseDate = dto.releaseDate ?: "",
            title = dto.title ?: "",
            voteAverage = dto.voteAverage ?: 0.0,
            name = dto.name ?: "",
            rating = getRating(dto)
        )
    }

    fun convertToListMovie(dto: MoviesApiResponseDto): List<Movie> {
        return dto.results?.map { toViewObject(it) } ?: listOf()
    }
}
