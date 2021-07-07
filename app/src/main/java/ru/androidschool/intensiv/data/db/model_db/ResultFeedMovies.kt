package ru.androidschool.intensiv.data.db.model_db

data class ResultFeedMovies<T>(
    val titleRes: Int,
    val movies: List<T>
)
