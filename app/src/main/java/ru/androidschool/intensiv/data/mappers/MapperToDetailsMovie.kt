package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.dto.details_movie_dto.DetailsMovieDto
import ru.androidschool.intensiv.data.vo.DetailsMovie

object MapperToDetailsMovie {
    fun convertToDetailsMovie(dto: DetailsMovieDto): DetailsMovie {
        return DetailsMovie(
            id = dto.id,
            originalTitle = dto.originalTitle,
            overview = dto.overview,
            popularity = dto.popularity,
            posterPath = dto.posterPath,
            releaseDate = dto.releaseDate,
            title = dto.title,
            voteAverage = dto.voteAverage,
            backdropPath = dto.backdropPath,
            genreDtos = dto.genres,
            homepage = dto.homepage,
            originalLanguage = dto.originalLanguage,
            productionCompanyDtos = dto.productionCompany,
            productionCountryDtos = dto.productionCountry,
            revenue = dto.revenue,
            runtime = dto.runtime,
            spokenLanguageDtos = dto.spokenLanguage,
            status = dto.status,
            tagline = dto.tagline,
            video = dto.video,
            voteCount = dto.voteCount
        )
    }
}
