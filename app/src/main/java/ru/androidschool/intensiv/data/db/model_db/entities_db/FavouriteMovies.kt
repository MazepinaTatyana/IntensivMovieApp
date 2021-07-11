package ru.androidschool.intensiv.model.db_movie_model

import androidx.room.Embedded
import androidx.room.Relation

data class FavouriteMovies(
    @Embedded val favouriteMovie: FavouriteMoviesEntity,
    @Relation(
        parentColumn = "favouriteMovieId",
        entityColumn = "movieId"
    )
    val movie: Movie
)
