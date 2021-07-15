package ru.androidschool.intensiv.presentation.liked_movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.androidschool.intensiv.data.repository.db_repository.DbFavouriteMovieRepository

class LikedMovieModelFactory(private val dbFavouriteRepository: DbFavouriteMovieRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LikedMovieViewModel::class.java)) {
            return LikedMovieViewModel(dbFavouriteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
