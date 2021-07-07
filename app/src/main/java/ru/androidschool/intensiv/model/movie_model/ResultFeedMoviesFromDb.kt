package ru.androidschool.intensiv.model.movie_model

data class ResultFeedMoviesFromDb (
    val titleRes: Int,
    val movies: List<Movie>
)