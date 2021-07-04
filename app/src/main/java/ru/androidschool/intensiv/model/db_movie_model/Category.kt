package ru.androidschool.intensiv.model.db_movie_model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.androidschool.intensiv.ui.feed.MovieCategory

@Entity(tableName = "category")
data class Category (
    @PrimaryKey()
    var categoryId: Int = 0,

//    @SerializedName("title")
//    @Expose
//    var title: Int,
////
//    @SerializedName("movieCategory")
//    @Expose
//    var movieCategory: MovieCategory,
)