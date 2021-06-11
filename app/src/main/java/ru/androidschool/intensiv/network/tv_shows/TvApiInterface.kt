package ru.androidschool.intensiv.network.tv_shows

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.model.tv_show_model.TvShowRespounse

interface TvApiInterface {

    @GET("tv/popular")
    fun getTvShows(
        @Query("api_key") api_key: String = BuildConfig.THE_MOVIE_DATABASE_API,
        @Query("language") language: String = "ru"
    ): Call<TvShowRespounse>
}
