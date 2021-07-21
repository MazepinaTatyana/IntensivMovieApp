package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.constants.RATING
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieEntity
import ru.androidschool.intensiv.data.vo.Movie

object MapperDbToVo :
    BaseMapper<MovieEntity> {

    override fun toViewObject(movie: MovieEntity): Movie {
        return Movie(
            id = movie.id,
            originalTitle = movie.originalTitle,
            overview = movie.overview?.let { it } ?: "",
            popularity = movie.popularity,
            posterPath = movie.posterPath?.let { it } ?: "",
            releaseDate = movie.releaseDate,
            title = movie.title,
            voteAverage = movie.voteAverage,
            name = movie.name,
            rating = getRating(movie)
        )
    }
    fun getRating(movie: MovieEntity): Double {
        return movie.voteAverage?.let { it / RATING } ?: 0.0
    }
}
