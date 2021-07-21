package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.constants.RATING
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieEntity
import ru.androidschool.intensiv.data.dto.details_movie.DetailsMovieDto
import ru.androidschool.intensiv.data.dto.movie_dto.MovieDto

fun getRating (dto: MovieDto): Double {
    return dto.voteAverage?.let { it / RATING } ?: 0.0
}

fun getRating (dto: DetailsMovieDto): Double {
    return dto.voteAverage?.let { it / RATING } ?: 0.0
}

fun getRating (dto: MovieEntity): Double {
    return dto.voteAverage / RATING
}
