package ru.androidschool.intensiv.model.db_movie_model

import androidx.room.Embedded
import androidx.room.Relation

data class MovieAndFavouriteMovie (

    @Relation(parentColumn = "id", entityColumn = "movieId")
//    @Embedded
    val movie: Movie
)