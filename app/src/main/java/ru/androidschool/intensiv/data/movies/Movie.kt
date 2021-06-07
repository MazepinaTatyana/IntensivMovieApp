package ru.androidschool.intensiv.data.movies

data class Movie(
    var title: String,
    var voteAverage: Double = 0.0
) {
    val rating: Float
        get() = voteAverage.div(2).toFloat()
}
