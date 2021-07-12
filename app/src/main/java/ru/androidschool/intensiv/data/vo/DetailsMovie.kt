package ru.androidschool.intensiv.data.vo

import ru.androidschool.intensiv.data.dto.details_movie_dto.GenreDto
import ru.androidschool.intensiv.data.dto.details_movie_dto.ProductionCompanyDto
import ru.androidschool.intensiv.data.dto.details_movie_dto.ProductionCountryDto
import ru.androidschool.intensiv.data.dto.details_movie_dto.SpokenLanguageDto

data class DetailsMovie(

    val backdropPath: String,
    val genreDtos: List<GenreDto>,
    val homepage: String,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanyDtos: List<ProductionCompanyDto>,
    val productionCountryDtos: List<ProductionCountryDto>,
    val releaseDate: String? = null,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguageDtos: List<SpokenLanguageDto>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
) {
    val rating: Double
        get() = voteAverage / 2
}
