package ru.androidschool.intensiv.model.db_movie_model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category (
    @PrimaryKey
    var categoryId: String,

//    @SerializedName("title")
//    @Expose
//    var title: Int,
////
//    @SerializedName("movieCategory")
//    @Expose
//    var movieCategory: MovieCategory,
)