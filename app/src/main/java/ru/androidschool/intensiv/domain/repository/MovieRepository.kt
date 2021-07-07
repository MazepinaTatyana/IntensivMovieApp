package ru.androidschool.intensiv.domain.repository

import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.vo.Movie

interface MovieRepository {

    fun getMovies(): Single<List<Movie>>

//    fun getPopularMovies(): Observable<*>
//    fun getNowPlayingMovies(): Observable<*>
//    fun getUpcomingMovies(): Observable<*>
}