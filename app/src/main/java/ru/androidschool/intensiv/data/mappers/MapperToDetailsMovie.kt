package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.dto.details_movie.*
import ru.androidschool.intensiv.data.vo.DetailsMovie

object MapperToDetailsMovie {
    fun convertToDetailsMovie(dto: DetailsMovieDto): DetailsMovie {
        return DetailsMovie(
            id = dto.id?.let { it } ?: 0,
            originalTitle = dto.originalTitle?.let { it } ?: "",
            overview = dto.overview?.let { it } ?: "",
            popularity = dto.popularity?.let { it } ?: 0.0,
            posterPath = dto.posterPath?.let { it } ?: "",
            releaseDate = dto.releaseDate?.let { it } ?: "",
            title = dto.title?.let { it } ?: "",
            voteAverage = dto.voteAverage?.let { it } ?: 0.0,
            backdropPath = dto.backdropPath?.let { it } ?: "",
            genreDtos = (dto.genres?.let { it } ?: "") as List<GenreDto>,
            homepage = dto.homepage?.let { it } ?: "",
            originalLanguage = dto.originalLanguage?.let { it } ?: "",
            productionCompanyDtos = (dto.productionCompany?.let { it } ?: "") as List<ProductionCompanyDto>,
            productionCountryDtos = (dto.productionCountry?.let { it } ?: "") as List<ProductionCountryDto>,
            revenue = dto.revenue?.let { it } ?: 0,
            runtime = dto.runtime?.let { it } ?: 0,
            spokenLanguageDtos = (dto.spokenLanguage?.let { it } ?: "") as List<SpokenLanguageDto>,
            status = dto.status?.let { it } ?: "",
            tagline = dto.tagline?.let { it } ?: "",
            video = dto.video?.let { it } ?: false,
            voteCount = dto.voteCount?.let { it } ?: 0
        )
    }
}
