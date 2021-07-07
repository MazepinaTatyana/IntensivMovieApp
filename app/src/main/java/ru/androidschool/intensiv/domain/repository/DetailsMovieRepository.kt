package ru.androidschool.intensiv.domain.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.vo.DetailsMovie

interface DetailsMovieRepository {

    fun getDetailsMovieById(movieId: Int): Single<DetailsMovie>
}