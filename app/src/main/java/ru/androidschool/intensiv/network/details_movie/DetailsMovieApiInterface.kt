package ru.androidschool.intensiv.network.details_movie

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.model.details_movie_model.DetailsMovieModel
import ru.androidschool.intensiv.ui.movie_details.ActorResponse

interface DetailsMovieApiInterface {

    @GET("movie/{movie_id}")
    fun getDetailsMovieById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = BuildConfig.THE_MOVIE_DATABASE_API,
        @Query("language") language: String = "ru"
    ): Call<DetailsMovieModel>

    @GET("movie/{movie_id}/credits")
    fun getMovieActors(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = BuildConfig.THE_MOVIE_DATABASE_API,
        @Query("language") language: String = "ru"
    ): Call<ActorResponse>
}
