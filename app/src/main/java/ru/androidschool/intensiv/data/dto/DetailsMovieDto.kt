package ru.androidschool.intensiv.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.androidschool.intensiv.data.vo.Genre
import ru.androidschool.intensiv.data.vo.ProductionCompany
import ru.androidschool.intensiv.data.vo.ProductionCountry
import ru.androidschool.intensiv.data.vo.SpokenLanguage

data class DetailsMovieDto(
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String,
    @SerializedName("genres")
    @Expose
    val genres: List<Genre>,
    @SerializedName("homepage")
    @Expose
    val homepage: String,
    @SerializedName("id")
    @Expose
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
    @SerializedName("production_companies")
    @Expose
    val productionCompany: List<ProductionCompany>,
    @SerializedName("production_countries")
    @Expose
    val productionCountry: List<ProductionCountry>,
    @SerializedName("release_date")
    @Expose
    val releaseDate: String,
    @SerializedName("revenue")
    @Expose
    val revenue: Int,
    @SerializedName("runtime")
    @Expose
    val runtime: Int,
    @SerializedName("spoken_languages")
    @Expose
    val spokenLanguage: List<SpokenLanguage>,
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("tagline")
    @Expose
    val tagline: String,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("video")
    @Expose
    val video: Boolean,
    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double,
    @SerializedName("vote_count")
    @Expose
    val voteCount: Int
)