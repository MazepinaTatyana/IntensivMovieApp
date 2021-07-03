package ru.androidschool.intensiv.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.androidschool.intensiv.model.db_movie_model.FavouriteMovies
import ru.androidschool.intensiv.model.db_movie_model.Movie
import ru.androidschool.intensiv.model.db_movie_model.Category

@Database(entities = [Movie::class, Category::class, FavouriteMovies::class], version = 4)
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
    abstract fun getMoviesByCategoryDao(): MoviesByCategoryDao
}