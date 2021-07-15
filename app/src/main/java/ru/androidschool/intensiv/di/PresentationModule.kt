package ru.androidschool.intensiv.di

import org.koin.dsl.module
import ru.androidschool.intensiv.presentation.liked_movies.LikedMovieViewModel
import ru.androidschool.intensiv.presentation.movie_details.MovieDetailsFragment
import ru.androidschool.intensiv.presentation.profile.ProfileFragment

val presentationModule = module {
    factory { MovieDetailsFragment() }
    factory { LikedMovieViewModel() }
    factory { ProfileFragment() }
}
