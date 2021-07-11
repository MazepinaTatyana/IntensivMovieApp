package ru.androidschool.intensiv.data

import ru.androidschool.intensiv.data.movies.MovieVo
import ru.androidschool.intensiv.model.db_movie_model.Movie

object MapperToMovieDb {
    fun convertTo(vo: MovieVo): Movie {
        return Movie(
            id = vo.id,
            backdropPath = vo.backdropPath?.let { it } ?: "",
            originalLanguage = vo.originalLanguage,
            originalTitle = vo.originalTitle,
            overview = vo.overview?.let { it } ?: "",
            popularity = vo.popularity,
            posterPath = vo.posterPath?.let { it } ?: "",
            releaseDate = vo.releaseDate,
            title = vo.title,
            voteAverage = vo.voteAverage,
            voteCount = vo.voteCount,
            status = vo.status?.let { it } ?: "",
            revenue = vo.revenue,
            runtime = vo.runtime?.let { it } ?: 0,
            tagline = vo.tagline?.let { it } ?: "",
            homepage = vo.homepage?.let { it } ?: ""
        )
    }
}
