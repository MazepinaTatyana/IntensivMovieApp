package ru.androidschool.intensiv.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.data.db.model_db.entities_db.FavouriteMovies
import ru.androidschool.intensiv.data.db.model_db.entities_db.FavouriteMoviesEntity

interface DbFavouriteMoviesRepository {
    fun getFavouriteMovies(): Single<List<FavouriteMovies>>

    fun getFavouriteMovieById(id: Int): Single<FavouriteMovies>

    fun saveFavouriteMovie(movie: FavouriteMoviesEntity): Completable

    fun deleteFavouriteMovie(movie: FavouriteMoviesEntity): Completable
}