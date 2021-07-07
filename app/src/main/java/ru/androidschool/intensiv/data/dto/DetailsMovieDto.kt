package ru.androidschool.intensiv.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DetailsMovieDto(
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String,
    @SerializedName("genres")
    @Expose
    val genreDtos: List<GenreDto>,
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
    val productionCompanyDtos: List<ProductionCompanyDto>,
    @SerializedName("production_countries")
    @Expose
    val productionCountryDtos: List<ProductionCountryDto>,
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
    val spokenLanguageDtos: List<SpokenLanguageDto>,
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