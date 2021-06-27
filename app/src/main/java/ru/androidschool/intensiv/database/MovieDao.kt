package ru.androidschool.intensiv.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import ru.androidschool.intensiv.model.db_movie_model.Movie

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
}
