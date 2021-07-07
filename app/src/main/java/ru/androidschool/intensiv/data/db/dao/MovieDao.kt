package ru.androidschool.intensiv.data.db.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.db.model_db.CategoryWithMovies
import ru.androidschool.intensiv.data.db.model_db.entities_db.Movie

@Dao
interface MovieDao {

    @Insert
    fun saveMovie(movie: Movie): Completable

    @Delete
    fun deleteMovie(movie: Movie): Completable

    @Query("SELECT * FROM movies")
    fun getMovies(): Single<List<Movie>>

    @Query("SELECT * FROM movies WHERE movieId =:movieId")
    fun getMovieById(movieId: Int): Observable<Movie>

    @Transaction
    @Query("SELECT * FROM category")
    fun getMoviesByCategory(): Single<List<CategoryWithMovies>>

}
