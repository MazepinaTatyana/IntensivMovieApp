package ru.androidschool.intensiv.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.data.dto.details_movie.ActorResponseDto
import ru.androidschool.intensiv.data.dto.details_movie.DetailsMovieDto
import ru.androidschool.intensiv.data.dto.movie_dto.MoviesApiResponseDto

interface MovieApiInterface {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") api_key: String = BuildConfig.THE_MOVIE_DATABASE_API,
        @Query("language") language: String = BuildConfig.LANGUAGE_RU
    ): Single<MoviesApiResponseDto>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") api_key: String = BuildConfig.THE_MOVIE_DATABASE_API,
        @Query("language") language: String = BuildConfig.LANGUAGE_RU,
        @Query("region") region: String = BuildConfig.REGION_RU
    ): Single<MoviesApiResponseDto>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") api_key: String = BuildConfig.THE_MOVIE_DATABASE_API,
        @Query("language") language: String = BuildConfig.LANGUAGE_RU,
        @Query("region") region: String = BuildConfig.REGION_RU
    ): Single<MoviesApiResponseDto>

    @GET("tv/popular")
    suspend fun getTvShows(
        @Query("api_key") api_key: String = BuildConfig.THE_MOVIE_DATABASE_API,
        @Query("language") language: String = BuildConfig.LANGUAGE_RU
    ): MoviesApiResponseDto

    @GET("movie/{movie_id}")
    fun getDetailsMovieById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = BuildConfig.THE_MOVIE_DATABASE_API,
        @Query("language") language: String = BuildConfig.LANGUAGE_RU
    ): Single<DetailsMovieDto>

    @GET("movie/{movie_id}/credits")
    fun getMovieActors(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = BuildConfig.THE_MOVIE_DATABASE_API,
        @Query("language") language: String = BuildConfig.LANGUAGE_RU
    ): Single<ActorResponseDto>
}
