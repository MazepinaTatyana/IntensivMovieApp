package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.vo.Movie
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieDb

object MapperToMovieDb {
    fun convertTo(vo: Movie): MovieDb {
        return MovieDb(
            id = vo.id,
            originalTitle = vo.originalTitle?.let { it } ?: "",
            overview = vo.overview?.let { it } ?: "",
            popularity = vo.popularity,
            posterPath = vo.posterPath?.let { it } ?: "",
            releaseDate = vo.releaseDate?.let { it } ?: "",
            title = vo.title?.let { it } ?: "",
            voteAverage = vo.voteAverage
        )
    }
}
