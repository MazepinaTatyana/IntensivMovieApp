package ru.androidschool.intensiv.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.model.db_movie_model.FavouriteMovies
import ru.androidschool.intensiv.model.db_movie_model.FavouriteMoviesEntity

@Dao
interface FavouriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavouriteMovie(movieId: FavouriteMoviesEntity): Completable

    @Delete
    fun deleteFavouriteMovie(movie: FavouriteMoviesEntity): Completable

    @Query("SELECT * FROM favourite_movies")
    fun getFavouriteMovies(): Single<List<FavouriteMovies>>

    @Query("SELECT * FROM favourite_movies WHERE movieId = :id")
    fun getFavouriteMovieById(id: Int): Single<FavouriteMovies>
}