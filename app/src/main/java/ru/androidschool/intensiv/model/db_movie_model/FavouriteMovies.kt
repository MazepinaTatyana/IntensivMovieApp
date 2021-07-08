package ru.androidschool.intensiv.model.db_movie_model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favourite_movies")
data class FavouriteMovies(
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String,

    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "movieId")
    val id: Int,

    @SerializedName("original_language")
    @Expose
    val originalLanguage: String,

    @SerializedName("original_title")
    @Expose
    val originalTitle: String,

    @SerializedName("overview")
    @Expose
    val overview: String,

    @SerializedName("popularity")
    @Expose
    val popularity: Double,

    @SerializedName("poster_path")
    @Expose
    val posterPath: String,

    @SerializedName("release_date")
    @Expose
    val releaseDate: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double,

    @SerializedName("vote_count")
    @Expose
    val voteCount: Int,

    @SerializedName("homepage")
    @Expose
    val homepage: String,

    @SerializedName("revenue")
    @Expose
    val revenue: Int,

    @SerializedName("runtime")
    @Expose
    val runtime: Int,

    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("tagline")
    @Expose
    val tagline: String
)