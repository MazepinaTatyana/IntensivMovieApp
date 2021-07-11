package ru.androidschool.intensiv.model.movie_model

import ru.androidschool.intensiv.data.movies.MovieVo

data class ResultFeedMovies<T>(
    val titleRes: Int,
    val movies: List<MovieVo>
)
