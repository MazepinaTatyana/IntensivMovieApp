package ru.androidschool.intensiv.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.androidschool.intensiv.data.db.dao.MovieDao
import ru.androidschool.intensiv.data.db.model_db.entities_db.Category
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieAndCategoryCrossRef
import ru.androidschool.intensiv.data.db.model_db.entities_db.FavouriteMoviesEntity
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieDb
import ru.androidschool.intensiv.data.db.dao.FavouriteMovieDao
import ru.androidschool.intensiv.data.db.dao.MoviesByCategoryDao

@Database(entities = [MovieDb::class, Category::class, FavouriteMoviesEntity::class, MovieAndCategoryCrossRef::class], version = 9, exportSchema = false)
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
