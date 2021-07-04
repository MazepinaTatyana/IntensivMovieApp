package ru.androidschool.intensiv.model.movie_model

data class ResultFeedMovies<T>(
    val titleRes: Int,
    val movies: List<T>
) {

}
