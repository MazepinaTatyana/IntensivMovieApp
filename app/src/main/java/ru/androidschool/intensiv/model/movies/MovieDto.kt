package ru.androidschool.intensiv.model.movies


data class MovieDto(
    var id: Int,
    var title: String,
    var voteAverage: Double = 0.0,
    val backdropPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val voteCount: Int
) {
    val rating: Float
        get() = voteAverage.div(2).toFloat()
}
