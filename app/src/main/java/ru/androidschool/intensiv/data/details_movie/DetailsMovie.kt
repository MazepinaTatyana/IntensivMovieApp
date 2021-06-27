package ru.androidschool.intensiv.data.details_movie

import ru.androidschool.intensiv.ui.movie_details.Actor

data class DetailsMovie(
    var id: Int,
    var title: String,
    var voteAverage: Double = 0.0,
    var description: String,
    var genre: String,
    var studio: String,
    var year: String,
    var actors: List<Actor>
)
