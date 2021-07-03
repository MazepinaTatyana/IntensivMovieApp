package ru.androidschool.intensiv.model.db_movie_model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movies")
data class FavouriteMovies(
    @PrimaryKey(autoGenerate = true)
    val favouriteId: Int,
    val movieId: Int
)