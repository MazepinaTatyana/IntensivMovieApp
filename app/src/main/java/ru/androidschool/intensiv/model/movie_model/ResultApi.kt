package ru.androidschool.intensiv.model.movie_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.androidschool.intensiv.model.details_movie_model.Genre
import ru.androidschool.intensiv.model.details_movie_model.ProductionCompany
import ru.androidschool.intensiv.model.details_movie_model.ProductionCountry
import ru.androidschool.intensiv.model.details_movie_model.SpokenLanguage

data class ResultApi(

    @SerializedName("adult")
    @Expose
    val isAdult: Boolean,

    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String,

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
    val belongsToCollection: Any,

    @SerializedName("budget")
    @Expose
    val budget: Int,

    @SerializedName("genres")
    @Expose
    val genres: List<Genre>,

    @SerializedName("homepage")
    @Expose
    val homepage: String,

    @SerializedName("imdb_id")
    @Expose
    val imdbId: String,

    @SerializedName("production_companies")
    @Expose
    val productionCompanies: List<ProductionCompany>,

    @SerializedName("production_countries")
    @Expose
    val productionCountries: List<ProductionCountry>,

    @SerializedName("revenue")
    @Expose
    val revenue: Int,

    @SerializedName("runtime")
    @Expose
    val runtime: Int,

    @SerializedName("spoken_languages")
    @Expose
    val spokenLanguages: List<SpokenLanguage>,

    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("tagline")
    @Expose
    val tagline: String
) {
    val rating: Float
        get() = voteAverage.div(2).toFloat()
}
