package ru.androidschool.intensiv.data.repository.db_repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.db.database.MovieDatabase
import ru.androidschool.intensiv.data.db.model_db.entities_db.FavouriteMovies
import ru.androidschool.intensiv.data.db.model_db.entities_db.FavouriteMoviesEntity

class DbFavouriteMovieRepository(var database: MovieDatabase) {
    fun getFavouriteMovies(): Single<List<FavouriteMovies>> = database
        .getFavouriteMovieDao()
        .getFavouriteMovies()

    fun getFavouriteMovieById(id: Int): Single<FavouriteMovies> = database
        .getFavouriteMovieDao()
        .getFavouriteMovieById(id)

    fun saveFavouriteMovie(movie: FavouriteMoviesEntity) = database
        .getFavouriteMovieDao()
        .saveFavouriteMovie(movie)

    fun deleteFavouriteMovie(movie: FavouriteMoviesEntity) = database
        .getFavouriteMovieDao()
        .deleteFavouriteMovie(movie)
}
