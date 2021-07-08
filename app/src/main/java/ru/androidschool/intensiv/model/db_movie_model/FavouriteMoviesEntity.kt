package ru.androidschool.intensiv.model.db_movie_model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favourite_movies")
data class FavouriteMoviesEntity(

    @PrimaryKey(autoGenerate = true)
    val movieId: Int
//    val id: Int
)