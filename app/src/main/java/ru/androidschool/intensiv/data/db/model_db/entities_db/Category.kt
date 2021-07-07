package ru.androidschool.intensiv.data.db.model_db.entities_db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category (
    @PrimaryKey
    var categoryId: String,
)
