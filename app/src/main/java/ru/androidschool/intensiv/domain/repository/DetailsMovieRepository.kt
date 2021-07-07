package ru.androidschool.intensiv.domain.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.vo.DetailsMovie
import ru.androidschool.intensiv.data.vo.Movie

interface DetailsMovieRepository {

    fun getDetailsMovieById(movieId: Int): Single<Movie>
}