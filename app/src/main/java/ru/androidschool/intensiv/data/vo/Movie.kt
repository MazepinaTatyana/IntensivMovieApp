package ru.androidschool.intensiv.data.vo

data class Movie(
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val name: String,
    val calculatedRating: Double
)
