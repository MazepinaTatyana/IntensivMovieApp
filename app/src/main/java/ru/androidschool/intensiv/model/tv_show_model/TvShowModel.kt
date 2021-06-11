package ru.androidschool.intensiv.model.tv_show_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TvShowModel(
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String,
    @SerializedName("first_air_date")
    @Expose
    val firstAirDate: String,
    @SerializedName("genre_ids")
    @Expose
    val genreIds: List<Int>,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("origin_country")
    @Expose
    val originCountry: List<String>,
    @SerializedName("original_language")
    @Expose
    val originalLanguage: String,
    @SerializedName("original_name")
    @Expose
    val originalName: String,
    @SerializedName("overview")
    @Expose
    val overview: String,
    @SerializedName("popularity")
    @Expose
    val popularity: Double,
    @SerializedName("poster_path")
    @Expose
    val posterPath: String,
    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double,
    @SerializedName("vote_count")
    @Expose
    val voteCount: Int
) {
    val rating: Float
        get() = voteAverage.div(2).toFloat()
}
