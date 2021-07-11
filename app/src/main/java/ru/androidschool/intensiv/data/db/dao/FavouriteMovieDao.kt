package ru.androidschool.intensiv.data.db.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.data.db.model_db.entities_db.FavouriteMovies
import ru.androidschool.intensiv.data.db.model_db.entities_db.FavouriteMoviesEntity

@Dao
interface FavouriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavouriteMovie(movieId: FavouriteMoviesEntity): Completable

    @Delete
    fun deleteFavouriteMovie(movie: FavouriteMoviesEntity): Completable

    @Query("SELECT * FROM favourite_movies")
    fun getFavouriteMovies(): Single<List<FavouriteMovies>>

    @Query("SELECT * FROM favourite_movies WHERE favouriteMovieId = :id")
    fun getFavouriteMovieById(id: Int): Single<FavouriteMovies>
}
