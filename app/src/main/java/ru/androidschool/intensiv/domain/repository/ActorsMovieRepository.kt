package ru.androidschool.intensiv.domain.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.dto.details_movie.ActorResponseDto

interface ActorsMovieRepository {

    fun getActorsMovie(movieId: Int): Single<ActorResponseDto>
}
