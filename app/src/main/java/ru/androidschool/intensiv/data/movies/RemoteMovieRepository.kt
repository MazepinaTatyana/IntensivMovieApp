package ru.androidschool.intensiv.data.movies

import io.reactivex.Single
import ru.androidschool.intensiv.model.movie_model.ApiResponse
import ru.androidschool.intensiv.network.MovieApiClient

class RemoteMovieRepository : MovieRepository {

    override fun getPopularMovies(): Single<ApiResponse> = MovieApiClient.movieApiClient.getPopularMovies()

    override fun getNowPlayingMovies(): Single<ApiResponse> = MovieApiClient.movieApiClient.getNowPlayingMovies()

    override fun getUpcomingMovies(): Single<ApiResponse> = MovieApiClient.movieApiClient.getUpcomingMovies()
}
