package ru.androidschool.intensiv.data.db.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.data.db.model_db.entities_db.Category
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieAndCategoryCrossRef
import ru.androidschool.intensiv.data.db.model_db.CategoryWithMovies

@Dao
interface MoviesByCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMoviesByCategories(categoriesWithMovies: List<MovieAndCategoryCrossRef>): Completable

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
