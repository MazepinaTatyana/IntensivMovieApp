package ru.androidschool.intensiv.model.movie_model

import ru.androidschool.intensiv.model.db_movie_model.Movie

data class ResultFeedMoviesFromDb (
    val titleRes: Int,
    val movies: List<Movie>
)