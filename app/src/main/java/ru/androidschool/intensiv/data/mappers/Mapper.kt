package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.dto.MovieDto
import ru.androidschool.intensiv.data.dto.MoviesApiResponseDto
import ru.androidschool.intensiv.data.vo.DetailsMovie
import ru.androidschool.intensiv.data.vo.Movie

class Mapper {
    fun convertMovie(dto: DetailsMovie): Movie {
        return Movie(
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

    fun convertMovieFromDb(dto: DetailsMovie): ru.androidschool.intensiv.data.db.model_db.entities_db.Movie {
        return ru.androidschool.intensiv.data.db.model_db.entities_db.Movie(
            id = dto.id,
            originalTitle = dto.originalTitle?.let { it } ?: "",
            overview = dto.overview,
            popularity = dto.popularity,
            posterPath = dto.posterPath?.let { it } ?: "",
            releaseDate = dto.releaseDate?.let { it } ?: "",
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
            title = dto.title?.let { it } ?: "",
            voteAverage = dto.voteAverage,
            name = dto.name?.let { it } ?: ""
        )
    }

    fun toValueObjectForDbMovie(movie: Movie): ru.androidschool.intensiv.data.db.model_db.entities_db.Movie {
        return ru.androidschool.intensiv.data.db.model_db.entities_db.Movie(
            id = movie.id,
            originalTitle = movie.originalTitle?.let { it } ?: "",
            overview = movie.overview,
            popularity = movie.popularity,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate?.let { it } ?: "",
            title = movie.title?.let { it } ?: "",
            voteAverage = movie.voteAverage
        )
    }
}
