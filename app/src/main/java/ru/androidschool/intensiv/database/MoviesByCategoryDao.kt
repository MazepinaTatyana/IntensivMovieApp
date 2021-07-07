package ru.androidschool.intensiv.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.model.movie_model.Movie
import ru.androidschool.intensiv.model.db_movie_model.Category
import ru.androidschool.intensiv.model.db_movie_model.CategoryWithMovies
import ru.androidschool.intensiv.model.db_movie_model.MovieAndCategoryCrossRef

@Dao
interface MoviesByCategoryDao {

    @Update(entity = MovieAndCategoryCrossRef::class)
    fun saveMoviesByCategories(categoriesWithMovies: List<MovieAndCategoryCrossRef>): Completable

    @Update
    fun setMovies(listMovie: List<Movie>): Completable

    @Query("DELETE FROM category")
    fun deleteAllCategories(): Completable

    @Transaction
    @Query("SELECT * FROM category WHERE categoryId LIKE :categoryId")
    fun getCategoryWithMoviesById(categoryId: String): Single<CategoryWithMovies>

    @Insert
    fun setCategories(categories: List<Category>): Completable

    @Query("SELECT * FROM category")
    fun getCategories(): Single<List<Category>>

    @Query("SELECT * FROM category WHERE categoryId = :categoryId")
    fun getCategoryByMovieCategory(categoryId: Int): Single<Category>

    @Query("SELECT * FROM category")
    fun getCategoriesWithMovies(): Single<List<CategoryWithMovies>>
}