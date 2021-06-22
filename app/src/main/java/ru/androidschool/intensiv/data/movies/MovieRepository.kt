package ru.androidschool.intensiv.data.movies

import io.reactivex.Observable
import ru.androidschool.intensiv.model.movie_model.ApiResponse
import ru.androidschool.intensiv.network.MovieApiClient

object MovieRepository {

    fun getNowPlayingMovies(): Observable<ApiResponse> = MovieApiClient.movieApiClient.getNowPlayingMovies()
    fun getPopularMovies(): Observable<ApiResponse> = MovieApiClient.movieApiClient.getPopularMovies()
    fun getUpcomingMovies(): Observable<ApiResponse> = MovieApiClient.movieApiClient.getUpcomingMovies()
}
