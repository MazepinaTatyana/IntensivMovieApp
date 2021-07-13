package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.vo.Movie
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieEntity

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
            name = movie.name
        )
    }
}
