package ru.androidschool.intensiv.model.db_movie_model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class FavouriteMovies (
    @Embedded val favouriteMovie: FavouriteMoviesEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "movieId"
    )
    val movie: Movie
)