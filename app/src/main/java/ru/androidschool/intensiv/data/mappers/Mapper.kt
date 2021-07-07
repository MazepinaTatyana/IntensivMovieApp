package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.dto.MovieDto
import ru.androidschool.intensiv.data.dto.MoviesApiResponseDto
import ru.androidschool.intensiv.data.vo.DetailsMovie
import ru.androidschool.intensiv.data.vo.Movie

class Mapper {
    fun convertMovie(dto: DetailsMovie): ru.androidschool.intensiv.model.movie_model.Movie {
        return ru.androidschool.intensiv.model.movie_model.Movie(
            id = dto.id,
            originalTitle = dto.originalTitle,
            overview = dto.overview,
            popularity = dto.popularity,
            posterPath = dto.posterPath,
            releaseDate = dto.releaseDate,
            title = dto.title,
            voteAverage = dto.voteAverage
        )
    }

    fun toValueObject(dto: MoviesApiResponseDto): List<Movie> {
        return dto.results.map { toValueObject(it) }
    }

    private fun toValueObject(dto: MovieDto): Movie {
        return Movie(
            id = dto.id,
            originalTitle = dto.originalTitle,
            overview = dto.overview?.let { it } ?: "",
            popularity = dto.popularity,
            posterPath = dto.posterPath?.let { it } ?: "",
            releaseDate = dto.releaseDate,
            title = dto.title,
            voteAverage = dto.voteAverage,
        )
    }

    fun toValueObjectForDbMovie(movie: Movie): ru.androidschool.intensiv.model.movie_model.Movie {
      return ru.androidschool.intensiv.model.movie_model.Movie(
                id = movie.id,
                originalTitle = movie.originalTitle,
                overview = (movie.overview?.let { movie } ?: "") as String,
                popularity = movie.popularity,
                posterPath = (movie.posterPath?.let { movie } ?: "") as String,
                releaseDate = movie.releaseDate,
                title = movie.title,
                voteAverage = movie.voteAverage
            )

        }
}
