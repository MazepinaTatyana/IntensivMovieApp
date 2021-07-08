package ru.androidschool.intensiv.data.movies


data class MovieVo(
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
    val voteCount: Int,
    val status: String,
    val revenue: Int,
    val runtime: Int,
    val tagline: String,
    val homepage: String

) {
    val rating: Float
        get() = voteAverage.div(2).toFloat()
}
