package ru.androidschool.intensiv.model.db_movie_model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey
    var categoryId: String
)
