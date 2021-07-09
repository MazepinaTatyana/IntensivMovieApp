package ru.androidschool.intensiv.data.movies

import io.reactivex.Single

interface MovieRepository {

    fun getPopularMovies(): Single<*>
    fun getNowPlayingMovies(): Single<*>
    fun getUpcomingMovies(): Single<*>
}
