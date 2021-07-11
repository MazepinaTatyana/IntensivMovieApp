package ru.androidschool.intensiv.model.db_movie_model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movies")
data class FavouriteMoviesEntity(

    @PrimaryKey(autoGenerate = true)
    val favouriteMovieId: Int
)
