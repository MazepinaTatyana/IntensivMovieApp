package ru.androidschool.intensiv.data.db.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieEntity
import ru.androidschool.intensiv.data.db.model_db.CategoryWithMovies

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movieEntity: MovieEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMovies(listMovieEntity: List<MovieEntity>): Completable

    @Delete
    fun deleteMovie(movieEntity: MovieEntity): Completable

    @Query("SELECT * FROM movies")
    fun getMovies(): Observable<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    fun getMovieById(movieId: Int): Single<MovieEntity>

    @Transaction
    @Query("SELECT * FROM category")
    fun getMoviesByCategory(): Observable<List<CategoryWithMovies>>
}
