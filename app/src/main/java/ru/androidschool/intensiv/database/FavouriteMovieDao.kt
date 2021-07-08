package ru.androidschool.intensiv.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.movies.MovieVo
import ru.androidschool.intensiv.model.db_movie_model.Movie

@Dao
interface FavouriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavouriteMovie(movieId: Int): Completable

    @Delete
    fun deleteFavouriteMovie(movieId: Int): Completable

    @Query("SELECT movieId FROM favourite_movies")
    fun getFavouriteMovies(): Single<List<Int>>

    @Query("SELECT * FROM movies WHERE movieId = :id")
    fun getFavouriteMovieById(id: Int): Single<MovieVo>
}