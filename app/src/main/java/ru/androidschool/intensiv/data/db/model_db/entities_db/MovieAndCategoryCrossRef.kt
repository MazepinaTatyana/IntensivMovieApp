package ru.androidschool.intensiv.data.db.model_db.entities_db

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "categoryId"])
data class MovieAndCategoryCrossRef (
    val movieId: Int,
    val categoryId: String
)
