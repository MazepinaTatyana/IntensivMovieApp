package ru.androidschool.intensiv.di

import org.koin.dsl.module
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.data.network.MovieApiInterface

val apiModule = module {
    single<MovieApiInterface> { MovieApiClient.movieApiClient }
}
