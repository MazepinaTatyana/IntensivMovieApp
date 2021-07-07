package ru.androidschool.intensiv.data.vo

data class Movie(
    val id: Int,
    val originalTitle: String? = null,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String? = null,
    val title: String? = null,
    val voteAverage: Double,
    val name: String? = null
) {
    val rating: Double
        get() = voteAverage / 2
}
