package ru.androidschool.intensiv.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.androidschool.intensiv.model.db_movie_model.FavouriteMovies
import ru.androidschool.intensiv.model.db_movie_model.Movie
import ru.androidschool.intensiv.model.db_movie_model.Category
import ru.androidschool.intensiv.model.db_movie_model.MovieAndCategoryCrossRef

@Database(entities = [Movie::class, Category::class, FavouriteMovies::class, MovieAndCategoryCrossRef::class], version = 6, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    companion object {
        private var DB: MovieDatabase? = null
        private val DB_NAME = "movies.db"

        @Synchronized
        fun getInstance(context: Context): MovieDatabase {
                DB?.let { return it }
                val instance = Room.databaseBuilder(
                    context, MovieDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                DB = instance
                return instance
        }
    }

    abstract fun getMovieDao(): MovieDao
    abstract fun getFavouriteMovieDao(): FavouriteMovieDao
    abstract fun getMoviesByCategoryDao(): MoviesByCategoryDao
}