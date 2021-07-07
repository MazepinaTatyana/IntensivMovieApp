package ru.androidschool.intensiv.domain.repository

import io.reactivex.Single
import ru.androidschool.intensiv.presentation.movie_details.ActorResponse

interface ActorsMovieRepository {

    fun getActorsMovie(movieId: Int): Single<ActorResponse>
}