package ru.androidschool.intensiv.presentation.liked_movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.androidschool.intensiv.data.repository.db_repository.DbFavouriteMoviesRepository

class LikedMovieModelFactory(private val dbFavouriteRepository: DbFavouriteMoviesRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LikedMovieViewModel::class.java)) {
            return LikedMovieViewModel(dbFavouriteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
