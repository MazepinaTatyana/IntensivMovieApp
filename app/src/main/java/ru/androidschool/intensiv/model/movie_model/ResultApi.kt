package ru.androidschool.intensiv.model.movie_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.androidschool.intensiv.data.dto.GenreDto
import ru.androidschool.intensiv.data.dto.ProductionCompanyDto
import ru.androidschool.intensiv.data.dto.ProductionCountryDto
import ru.androidschool.intensiv.data.dto.SpokenLanguageDto

data class ResultApi(

    @SerializedName("adult")
    @Expose
    val isAdult: Boolean,

    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String? = null,

    @SerializedName("genre_ids")
    @Expose
    val genreIds: List<Int>,

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
    val overview: String? = null,

    @SerializedName("popularity")
    @Expose
    val popularity: Double,

    @SerializedName("poster_path")
    @Expose
    val posterPath: String? = null,

    @SerializedName("release_date")
    @Expose
    val releaseDate: String,

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
    val voteCount: Int,

    @SerializedName("first_air_date")
    @Expose
    val firstAirDate: String,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("origin_country")
    @Expose
    val originCountry: List<String>,

    @SerializedName("original_name")
    @Expose
    val originalName: String,

    @SerializedName("belongs_to_collection")
    @Expose
    val belongsToCollection: Any? = null,

    @SerializedName("budget")
    @Expose
    val budget: Int,

    @SerializedName("genres")
    @Expose
    val genreDtos: List<GenreDto>,

    @SerializedName("homepage")
    @Expose
    val homepage: String? = null,

    @SerializedName("imdb_id")
    @Expose
    val imdbId: String? = null,

    @SerializedName("production_companies")
    @Expose
    val productionCompanyDtos: List<ProductionCompanyDto>,

    @SerializedName("production_countries")
    @Expose
    val productionCountryDtos: List<ProductionCountryDto>,

    @SerializedName("revenue")
    @Expose
    val revenue: Int,

    @SerializedName("runtime")
    @Expose
    val runtime: Int? = null,

    @SerializedName("spoken_languages")
    @Expose
    val spokenLanguageDtos: List<SpokenLanguageDto>,

    @SerializedName("status")
    @Expose
    val status: String? = null,

    @SerializedName("tagline")
    @Expose
    val tagline: String? = null
) {
    val rating: Float
        get() = voteAverage.div(2).toFloat()
}
