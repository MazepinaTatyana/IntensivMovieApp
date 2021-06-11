package ru.androidschool.intensiv.network.tv_shows

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.network.CustomHttpLogging

object TvApiClient {

    private val tvShowClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor(CustomHttpLogging()).apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    val tvShowApiClient: TvApiInterface by lazy {

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(tvShowClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return@lazy retrofit.create(TvApiInterface::class.java)
    }
}
