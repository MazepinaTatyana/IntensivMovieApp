package ru.androidschool.intensiv.network

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.model.details_movie_model.DetailsMovieModel
import ru.androidschool.intensiv.model.movie_model.ApiResponse
import ru.androidschool.intensiv.ui.movie_details.ActorResponse

interface MovieApiInterface {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") api_key: String = BuildConfig.THE_MOVIE_DATABASE_API,
        @Query("language") language: String = BuildConfig.LANGUAGE_RU
    ): Observable<ApiResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") api_key: String = BuildConfig.THE_MOVIE_DATABASE_API,
        @Query("language") language: String = BuildConfig.LANGUAGE_RU,
        @Query("region") region: String = BuildConfig.REGION_RU
    ): Observable<ApiResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") api_key: String = BuildConfig.THE_MOVIE_DATABASE_API,
        @Query("language") language: String = BuildConfig.LANGUAGE_RU,
        @Query("region") region: String = BuildConfig.REGION_RU
    ): Observable<ApiResponse>

    @GET("tv/popular")
    fun getTvShows(
        @Query("api_key") api_key: String = BuildConfig.THE_MOVIE_DATABASE_API,
        @Query("language") language: String = BuildConfig.LANGUAGE_RU
    ): Observable<ApiResponse>

    @GET("movie/{movie_id}")
    fun getDetailsMovieById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = BuildConfig.THE_MOVIE_DATABASE_API,
        @Query("language") language: String = BuildConfig.LANGUAGE_RU
    ): Observable<DetailsMovieModel>

    @GET("movie/{movie_id}/credits")
    fun getMovieActors(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = BuildConfig.THE_MOVIE_DATABASE_API,
        @Query("language") language: String = BuildConfig.LANGUAGE_RU
    ): Observable<ActorResponse>
}
