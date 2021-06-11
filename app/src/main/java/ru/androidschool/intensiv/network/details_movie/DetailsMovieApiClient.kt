package ru.androidschool.intensiv.network.details_movie

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.network.CustomHttpLogging

object DetailsMovieApiClient {

    private val detailsMovieClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor(CustomHttpLogging()).apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    val detailsMovieApiClient: DetailsMovieApiInterface by lazy {

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(detailsMovieClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return@lazy retrofit.create(DetailsMovieApiInterface::class.java)
    }
}
