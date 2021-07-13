package ru.androidschool.intensiv.data.db.model_db.entities_db

import androidx.room.Embedded
import androidx.room.Relation

data class FavouriteMovies(
    @Embedded val favouriteMovie: FavouriteMoviesEntity,
    @Relation(
        parentColumn = "favouriteMovieId",
        entityColumn = "movieId"
    )
    val movie: MovieEntity
)
