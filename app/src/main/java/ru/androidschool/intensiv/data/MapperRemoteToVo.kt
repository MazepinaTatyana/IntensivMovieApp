package ru.androidschool.intensiv.data

import ru.androidschool.intensiv.data.movies.MovieVo
import ru.androidschool.intensiv.model.movie_model.ResultApi

object MapperRemoteToVo : BaseMapper<ResultApi> {

    override fun toViewObject(dto: ResultApi): MovieVo {
        return MovieVo(
            id = dto.id,
            backdropPath = dto.backdropPath?.let { it } ?: "",
            originalLanguage = dto.originalLanguage,
            originalTitle = dto.originalTitle,
            overview = dto.overview?.let { it } ?: "",
            popularity = dto.popularity,
            posterPath = dto.posterPath?.let { it } ?: "",
            releaseDate = dto.releaseDate,
            title = dto.title,
            voteAverage = dto.voteAverage,
            voteCount = dto.voteCount,
            status = dto.status?.let { it } ?: "",
            revenue = dto.revenue,
            runtime = dto.runtime?.let { it } ?: 0,
            tagline = dto.tagline?.let { it } ?: "",
            homepage = dto.homepage?.let { it } ?: ""
        )
    }
}
