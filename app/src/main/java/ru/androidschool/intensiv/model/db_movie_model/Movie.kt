package ru.androidschool.intensiv.model.db_movie_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    val backdropPath: String,
    @PrimaryKey
    @ColumnInfo(name = "movieId")
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val homepage: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String
)
