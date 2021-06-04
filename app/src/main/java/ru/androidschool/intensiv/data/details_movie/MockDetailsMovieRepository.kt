package ru.androidschool.intensiv.data.details_movie

import ru.androidschool.intensiv.ui.movie_details.Actor

object MockDetailsMovieRepository {

    fun getDetailsMovie(): DetailsMovie {
        val actorList = mutableListOf<Actor>()
        for (x in 0..5) {
            val actor = Actor(
                name = "Willem Dafoe"
            )
            actorList.add(actor)
        }

        return DetailsMovie(
            title = "Spider-Man",
            voteAverage = 3.0,
            description = "Spider-Man",
            genre = "Fantasy",
            studio = "Studio",
            year = "2018",
            actors = actorList
        )
    }
}