package ru.androidschool.intensiv.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.model.db_movie_model.CategoryWithMovies
import ru.androidschool.intensiv.model.db_movie_model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movie: Movie): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMovies(listMovie: List<Movie>): Completable

    @Delete
    fun deleteMovie(movie: Movie): Completable

    @Query("SELECT * FROM movies")
    fun getMovies(): Observable<List<Movie>>

    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    fun getMovieById(movieId: Int): Single<Movie>

    @Transaction
    @Query("SELECT * FROM category")
    fun getMoviesByCategory(): Observable<List<CategoryWithMovies>>
}
