package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieFromDb
import ru.androidschool.intensiv.data.dto.details_movie_dto.DetailsMovieDto
import ru.androidschool.intensiv.data.dto.movie_dto.MovieDto
import ru.androidschool.intensiv.data.dto.movie_dto.MoviesApiResponseDto
import ru.androidschool.intensiv.data.vo.*

class Mapper {
    fun convertToDetailsMovie(dto: DetailsMovieDto): DetailsMovie {
        return DetailsMovie(
            id = dto.id,
            originalTitle = dto.originalTitle,
            overview = dto.overview,
            popularity = dto.popularity,
            posterPath = dto.posterPath,
            releaseDate = dto.releaseDate,
            title = dto.title,
            voteAverage = dto.voteAverage,
            backdropPath = dto.backdropPath,
            genreDtos = dto.genres,
            homepage = dto.homepage,
            originalLanguage = dto.originalLanguage,
            productionCompanyDtos = dto.productionCompany,
            productionCountryDtos = dto.productionCountry,
            revenue = dto.revenue,
            runtime = dto.runtime,
            spokenLanguageDtos = dto.spokenLanguage,
            status = dto.status,
            tagline = dto.tagline,
            video = dto.video,
            voteCount = dto.voteCount
        )
    }

    fun convertToMovieFromDb(dto: DetailsMovie): MovieFromDb {
        return MovieFromDb(
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

    fun convertToMovieFromDb(movie: Movie): MovieFromDb {
        return MovieFromDb(
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
