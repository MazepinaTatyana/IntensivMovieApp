package ru.androidschool.intensiv.model.movies

import io.reactivex.Observable
import ru.androidschool.intensiv.data.dto.MoviesApiResponseDto
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.domain.repository.MovieRepository

//class RemoteMovieRepository: MovieRepository {
//
//    override fun getPopularMovies(): Observable<MoviesApiResponseDto> = MovieApiClient.movieApiClient.getPopularMovies()
//
//    override fun getNowPlayingMovies(): Observable<MoviesApiResponseDto> = MovieApiClient.movieApiClient.getNowPlayingMovies()
//
//    override fun getUpcomingMovies(): Observable<MoviesApiResponseDto> = MovieApiClient.movieApiClient.getUpcomingMovies()
//}