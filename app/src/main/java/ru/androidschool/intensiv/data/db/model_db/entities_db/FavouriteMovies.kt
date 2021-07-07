package ru.androidschool.intensiv.data.db.model_db.entities_db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movies")
data class FavouriteMovies(
    @PrimaryKey(autoGenerate = true)
    val favouriteId: Int,
    val movieId: Int
)
