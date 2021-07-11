package ru.androidschool.intensiv.model.db_movie_model

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "categoryId"])
data class MovieAndCategoryCrossRef(
    val movieId: Int,
    val categoryId: String
)
