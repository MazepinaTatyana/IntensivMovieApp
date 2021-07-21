package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.vo.Movie
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieEntity

object MapperToMovieDb {
    fun convertTo(vo: Movie): MovieEntity {
        return MovieEntity(
            id = vo.id,
            originalTitle = vo.originalTitle,
            overview = vo.overview,
            popularity = vo.popularity,
            posterPath = vo.posterPath,
            releaseDate = vo.releaseDate,
            title = vo.title,
            voteAverage = vo.voteAverage,
            name = vo.name
        )
    }
}
