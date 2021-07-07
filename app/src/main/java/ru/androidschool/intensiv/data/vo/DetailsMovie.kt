package ru.androidschool.intensiv.data.vo

data class DetailsMovie(

    val backdropPath: String,
    val genreDtos: List<Genre>,
    val homepage: String,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanyDtos: List<ProductionCompany>,
    val productionCountryDtos: List<ProductionCountry>,
    val releaseDate: String? = null,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguageDtos: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
){
    val rating: Double
        get() = voteAverage / 2
}