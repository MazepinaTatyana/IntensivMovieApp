package ru.androidschool.intensiv.domain.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.dto.details_movie_dto.ActorResponseDto

interface ActorsMovieRepository {

    fun getActorsMovie(movieId: Int): Single<ActorResponseDto>
}
