package ru.androidschool.intensiv.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import ru.androidschool.intensiv.model.db_movie_model.Movie
import ru.androidschool.intensiv.model.db_movie_model.Category
import ru.androidschool.intensiv.model.db_movie_model.CategoryWithMovies
import ru.androidschool.intensiv.ui.feed.MovieCategory

@Dao
interface MoviesByCategoryDao {
    @Insert
    fun saveMoviesByCategories(categoriesWithMovies: List<CategoryWithMovies>)

    @Query("SELECT * FROM category WHERE movieCategory = :categoryMovies")
    fun getCategoryWithMovies(categoryMovies: MovieCategory): Observable<CategoryWithMovies>

    @Query("SELECT * FROM category")
    fun getAllCategoriesWithMovies(): Observable<List<CategoryWithMovies>>

    @Insert
    fun setCategories(categories: List<Category>): Completable

    @Query("SELECT * FROM category")
    fun getCategories(): Observable<List<Category>>

    @Query("SELECT * FROM category WHERE movieCategory = :movieCategory")
    fun getCategoryByMovieCategory(movieCategory: MovieCategory): Observable<Category>
}