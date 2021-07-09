package ru.androidschool.intensiv.data

import ru.androidschool.intensiv.data.movies.MovieVo
import ru.androidschool.intensiv.model.db_movie_model.Movie
import ru.androidschool.intensiv.model.movie_model.ResultApi

class Mapper {

    fun convertToMovieVo(dto: ResultApi): MovieVo {
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

    fun convertToMovieDb(vo: MovieVo): Movie {
        return Movie(
            id = vo.id,
            backdropPath = vo.backdropPath?.let { it } ?: "",
            originalLanguage = vo.originalLanguage,
            originalTitle = vo.originalTitle,
            overview = vo.overview?.let { it } ?: "",
            popularity = vo.popularity,
            posterPath = vo.posterPath?.let { it } ?: "",
            releaseDate = vo.releaseDate,
            title = vo.title,
            voteAverage = vo.voteAverage,
            voteCount = vo.voteCount,
            status = vo.status?.let { it } ?: "",
            revenue = vo.revenue,
            runtime = vo.runtime?.let { it } ?: 0,
            tagline = vo.tagline?.let { it } ?: "",
            homepage = vo.homepage?.let { it } ?: ""

        )
    }

    fun convertToMovieVo(movie: Movie): MovieVo {
        return MovieVo(
            id = movie.id,
            backdropPath = movie.backdropPath?.let { it } ?: "",
            originalLanguage = movie.originalLanguage,
            originalTitle = movie.originalTitle,
            overview = movie.overview?.let { it } ?: "",
            popularity = movie.popularity,
            posterPath = movie.posterPath?.let { it } ?: "",
            releaseDate = movie.releaseDate,
            title = movie.title,
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount,
            status = movie.status?.let { it } ?: "",
            revenue = movie.revenue,
            runtime = movie.runtime?.let { it } ?: 0,
            tagline = movie.tagline?.let { it } ?: "",
            homepage = movie.homepage?.let { it } ?: ""

        )
    }
}
