package ru.androidschool.intensiv.data.db.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.db.model_db.CategoryWithMovies
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieFromDb

@Dao
interface MovieDao {

    @Insert
    fun saveMovie(movieFromDb: MovieFromDb): Completable

    @Delete
    fun deleteMovie(movieFromDb: MovieFromDb): Completable

    @Query("SELECT * FROM movies")
    fun getMovies(): Single<List<MovieFromDb>>

    @Query("SELECT * FROM movies WHERE movieId =:movieId")
    fun getMovieById(movieId: Int): Observable<MovieFromDb>

    @Transaction
    @Query("SELECT * FROM category")
    fun getMoviesByCategory(): Single<List<CategoryWithMovies>>

}
