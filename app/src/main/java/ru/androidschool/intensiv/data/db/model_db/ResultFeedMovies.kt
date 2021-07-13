package ru.androidschool.intensiv.data.db.model_db

import ru.androidschool.intensiv.data.vo.Movie

data class ResultFeedMovies(
    val titleRes: Int,
    val movies: List<Movie>
)
