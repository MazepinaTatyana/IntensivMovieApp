package ru.androidschool.intensiv.di

import org.koin.dsl.module
import ru.androidschool.intensiv.presentation.movie_details.MovieDetailsFragment

val presentationModule = module {
    factory { MovieDetailsFragment() }
}