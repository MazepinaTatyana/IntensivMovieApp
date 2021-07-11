package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.vo.Movie
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieDb


object MapperDbToVo :
    BaseMapper<MovieDb> {

    override fun toViewObject(movie: MovieDb): Movie {
        return Movie(
            id = movie.id,
            originalTitle = movie.originalTitle,
            overview = movie.overview?.let { it } ?: "",
            popularity = movie.popularity,
            posterPath = movie.posterPath?.let { it } ?: "",
            releaseDate = movie.releaseDate,
            title = movie.title,
            voteAverage = movie.voteAverage
        )
    }
}
