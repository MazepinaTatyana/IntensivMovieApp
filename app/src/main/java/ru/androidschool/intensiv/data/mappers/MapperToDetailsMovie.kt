package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.dto.details_movie.*
import ru.androidschool.intensiv.data.vo.DetailsMovie

object MapperToDetailsMovie {
    fun convertToDetailsMovie(dto: DetailsMovieDto): DetailsMovie {
        return DetailsMovie(
            id = dto.id ?: 0,
            originalTitle = dto.originalTitle ?: "",
            overview = dto.overview ?: "",
            popularity = dto.popularity ?: 0.0,
            posterPath = dto.posterPath ?: "",
            releaseDate = dto.releaseDate ?: "",
            title = dto.title ?: "",
            voteAverage = dto.voteAverage ?: 0.0,
            backdropPath = dto.backdropPath ?: "",
            genreDtos = dto.genres ?: listOf(),
            homepage = dto.homepage ?: "",
            originalLanguage = dto.originalLanguage ?: "",
            productionCompanyDtos = dto.productionCompany ?: listOf(),
            productionCountryDtos = dto.productionCountry ?: listOf(),
            revenue = dto.revenue ?: 0,
            runtime = dto.runtime ?: 0,
            spokenLanguageDtos = dto.spokenLanguage ?: listOf(),
            status = dto.status ?: "",
            tagline = dto.tagline ?: "",
            video = dto.video ?: false,
            voteCount = dto.voteCount ?: 0,
            rating = Rating().getRating(dto)
        )
    }
}
