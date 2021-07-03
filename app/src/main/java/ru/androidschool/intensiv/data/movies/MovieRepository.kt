package ru.androidschool.intensiv.data.movies

import io.reactivex.Observable

interface MovieRepository {

    fun getPopularMovies(): Observable<*>
    fun getNowPlayingMovies(): Observable<*>
    fun getUpcomingMovies(): Observable<*>
}