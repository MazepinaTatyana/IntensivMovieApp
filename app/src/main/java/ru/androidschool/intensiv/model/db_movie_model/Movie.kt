package ru.androidschool.intensiv.model.db_movie_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(

    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String? = null,

    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "movieId")
    val id: Int,

    @SerializedName("original_language")
    @Expose
    val originalLanguage: String? = null,

    @SerializedName("original_title")
    @Expose
    val originalTitle: String? = null,

    @SerializedName("overview")
    @Expose
    val overview: String? = null,

    @SerializedName("popularity")
    @Expose
    val popularity: Double? = null,

    @SerializedName("poster_path")
    @Expose
    val posterPath: String? = null,

    @SerializedName("release_date")
    @Expose
    val releaseDate: String? = null,

    @SerializedName("title")
    @Expose
    val title: String? = null,

    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double? = null,

    @SerializedName("vote_count")
    @Expose
    val voteCount: Int? = null,

    @SerializedName("homepage")
    @Expose
    val homepage: String? = null,

    @SerializedName("revenue")
    @Expose
    val revenue: Int? = null,

    @SerializedName("runtime")
    @Expose
    val runtime: Int? = null,

    @SerializedName("status")
    @Expose
    val status: String? = null,

    @SerializedName("tagline")
    @Expose
    val tagline: String? = null
) {
    val rating: Float?
        get() = voteAverage?.div(2)?.toFloat()
}

