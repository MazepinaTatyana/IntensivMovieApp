package ru.androidschool.intensiv.network.movies

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.model.movie_model.MovieApiResponse

interface MovieApiInterface {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") api_key: String = BuildConfig.THE_MOVIE_DATABASE_API,
        @Query("language") language: String = "ru"
    ): Call<MovieApiResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") api_key: String = BuildConfig.THE_MOVIE_DATABASE_API,
        @Query("language") language: String = "ru",
        @Query("region") region: String = "ru"
    ): Call<MovieApiResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") api_key: String = BuildConfig.THE_MOVIE_DATABASE_API,
        @Query("language") language: String = "ru",
        @Query("region") region: String = "ru"
    ): Call<MovieApiResponse>
}
