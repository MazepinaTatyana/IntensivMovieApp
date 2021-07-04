package ru.androidschool.intensiv.data

import ru.androidschool.intensiv.model.db_movie_model.Movie
import ru.androidschool.intensiv.model.details_movie_model.DetailsMovieModel
import ru.androidschool.intensiv.model.movie_model.ResultApi

class Mapper {
    fun convertMovie(dto: DetailsMovieModel): Movie {
        return Movie(
            id = dto.id,
            backdropPath = dto.backdropPath,
            originalLanguage = dto.originalLanguage,
            originalTitle = dto.originalTitle,
            overview = dto.overview,
            popularity = dto.popularity,
            posterPath = dto.posterPath,
            releaseDate = dto.releaseDate,
            title = dto.title,
            voteAverage = dto.voteAverage,
            voteCount = dto.voteCount,
            status = dto.status,
            revenue = dto.revenue,
            runtime = dto.runtime,
            tagline = dto.tagline,
            homepage = dto.homepage
        )
    }

    fun convertMovie(dto: ResultApi): Movie {
        return Movie(
            id = dto.id,
            backdropPath = dto.backdropPath?.let { it } ?: "",
            originalLanguage = dto.originalLanguage,
            originalTitle = dto.originalTitle,
            overview = dto.overview?.let { it } ?: "",
            popularity = dto.popularity,
            posterPath = dto.posterPath?.let { it } ?: "",
            releaseDate = dto.releaseDate,
            title = dto.title,
            voteAverage = dto.voteAverage,
            voteCount = dto.voteCount,
            status = dto.status?.let { it } ?: "",
            revenue = dto.revenue,
            runtime = dto.runtime?.let { it } ?: 0,
            tagline = dto.tagline?.let { it } ?: "",
            homepage = dto.homepage?.let { it } ?: ""
        )
    }
}