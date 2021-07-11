package ru.androidschool.intensiv.data.db.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieDb
import ru.androidschool.intensiv.data.db.model_db.CategoryWithMovies

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movieDb: MovieDb): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMovies(listMovieDb: List<MovieDb>): Completable

    @Delete
    fun deleteMovie(movieDb: MovieDb): Completable

    @Query("SELECT * FROM movies")
    fun getMovies(): Observable<List<MovieDb>>

    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    fun getMovieById(movieId: Int): Single<MovieDb>

    @Transaction
    @Query("SELECT * FROM category")
    fun getMoviesByCategory(): Observable<List<CategoryWithMovies>>
}
