package ru.androidschool.intensiv.domain.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.dto.movie_dto.MoviesApiResponseDto

interface MovieRepository {

    fun getMovies(): Single<MoviesApiResponseDto>

}