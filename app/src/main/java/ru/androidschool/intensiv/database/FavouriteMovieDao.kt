package ru.androidschool.intensiv.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.model.db_movie_model.Movie

@Dao
interface FavouriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavouriteMovie(movie: Movie): Completable

    @Delete
    fun deleteFavouriteMovie(movie: Movie): Completable

    @Query("SELECT * FROM movies")
    fun getFavouriteMovies(): Single<List<Movie>>
}