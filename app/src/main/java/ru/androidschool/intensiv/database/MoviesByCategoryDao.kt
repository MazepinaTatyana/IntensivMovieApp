package ru.androidschool.intensiv.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import ru.androidschool.intensiv.model.db_movie_model.Movie
import ru.androidschool.intensiv.model.db_movie_model.Category
import ru.androidschool.intensiv.model.db_movie_model.CategoryWithMovies
import ru.androidschool.intensiv.model.db_movie_model.MovieAndCategoryCrossRef
import ru.androidschool.intensiv.ui.feed.MovieCategory

@Dao
interface MoviesByCategoryDao {

    @Insert
    fun saveMoviesByCategories(categoriesWithMovies: List<MovieAndCategoryCrossRef>)

    @Update
    fun setMovies(listMovie: List<Movie>): Completable

    @Query("DELETE FROM category")
    fun deleteAllCategories(): Completable

    @Transaction
    @Query("SELECT * FROM category WHERE categoryId LIKE :categoryId")
    fun getCategoryWithMoviesById(categoryId: Int): Observable<CategoryWithMovies>

    @Insert
    fun setCategories(categories: List<Category>): Completable

    @Query("SELECT * FROM category")
    fun getCategories(): Observable<List<Category>>

    @Query("SELECT * FROM category WHERE categoryId = :categoryId")
    fun getCategoryByMovieCategory(categoryId: Int): Observable<Category>
}