package ru.androidschool.intensiv.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import ru.androidschool.intensiv.model.db_movie_model.CategoryWithMovies
import ru.androidschool.intensiv.model.db_movie_model.Movie
import ru.androidschool.intensiv.model.db_movie_model.MovieAndCategoryCrossRef

@Dao
interface MovieDao {

    @Insert
    fun saveMovie(movie: Movie): Completable

    @Delete
    fun deleteMovie(movie: Movie): Completable

    @Query("SELECT * FROM movies")
    fun getMovies(): Observable<List<Movie>>

    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    fun getMovieById(movieId: Int): Observable<Movie>

    @Transaction
    @Query("SELECT * FROM category")
    fun getMoviesByCategory(): Observable<List<CategoryWithMovies>>

}
