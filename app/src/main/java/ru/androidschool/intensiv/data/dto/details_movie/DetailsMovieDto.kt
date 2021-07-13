package ru.androidschool.intensiv.data.dto.details_movie

import com.google.gson.annotations.SerializedName

data class DetailsMovieDto(
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("genres")
    val genres: List<GenreDto>? = null,
    @SerializedName("homepage")
    val homepage: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("production_companies")
    val productionCompany: List<ProductionCompanyDto>? = null,
    @SerializedName("production_countries")
    val productionCountry: List<ProductionCountryDto>? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("revenue")
    val revenue: Int? = null,
    @SerializedName("runtime")
    val runtime: Int? = null,
    @SerializedName("spoken_languages")
    val spokenLanguage: List<SpokenLanguageDto>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("tagline")
    val tagline: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
)
