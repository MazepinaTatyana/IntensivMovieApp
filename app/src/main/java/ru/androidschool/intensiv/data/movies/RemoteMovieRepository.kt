package ru.androidschool.intensiv.data.movies

import io.reactivex.Observable
import ru.androidschool.intensiv.model.movie_model.ApiResponse
import ru.androidschool.intensiv.network.MovieApiClient

class RemoteMovieRepository: MovieRepository {

    override fun getPopularMovies(): Observable<ApiResponse> = MovieApiClient.movieApiClient.getPopularMovies()

    override fun getNowPlayingMovies(): Observable<ApiResponse> = MovieApiClient.movieApiClient.getNowPlayingMovies()

    override fun getUpcomingMovies(): Observable<ApiResponse> = MovieApiClient.movieApiClient.getUpcomingMovies()
}