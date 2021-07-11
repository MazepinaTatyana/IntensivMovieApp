package ru.androidschool.intensiv.data

import ru.androidschool.intensiv.data.movies.MovieVo
import ru.androidschool.intensiv.model.db_movie_model.Movie

object MapperDbToVo : BaseMapper<Movie> {

    override fun toViewObject(movie: Movie): MovieVo {
        return MovieVo(
            id = movie.id,
            backdropPath = movie.backdropPath?.let { it } ?: "",
            originalLanguage = movie.originalLanguage,
            originalTitle = movie.originalTitle,
            overview = movie.overview?.let { it } ?: "",
            popularity = movie.popularity,
            posterPath = movie.posterPath?.let { it } ?: "",
            releaseDate = movie.releaseDate,
            title = movie.title,
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount,
            status = movie.status?.let { it } ?: "",
            revenue = movie.revenue,
            runtime = movie.runtime?.let { it } ?: 0,
            tagline = movie.tagline?.let { it } ?: "",
            homepage = movie.homepage?.let { it } ?: ""
        )
    }
}
