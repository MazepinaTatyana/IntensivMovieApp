package ru.androidschool.intensiv.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.movies.MovieVo
import ru.androidschool.intensiv.model.db_movie_model.FavouriteMovies
import ru.androidschool.intensiv.model.db_movie_model.Movie
import ru.androidschool.intensiv.model.db_movie_model.MovieAndFavouriteMovie

@Dao
interface FavouriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavouriteMovie(movieId: FavouriteMovies): Completable

    @Delete
    fun deleteFavouriteMovie(movie: FavouriteMovies): Completable

    @Query("SELECT * FROM favourite_movies")
    fun getFavouriteMovies(): Single<List<FavouriteMovies>>

    @Query("SELECT * FROM favourite_movies WHERE movieId = :id")
    fun getFavouriteMovieById(id: Int): Single<FavouriteMovies>
}