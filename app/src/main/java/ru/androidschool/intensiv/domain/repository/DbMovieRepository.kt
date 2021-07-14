package ru.androidschool.intensiv.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import ru.androidschool.intensiv.data.db.model_db.CategoryWithMovies
import ru.androidschool.intensiv.data.db.model_db.entities_db.Category
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieAndCategoryCrossRef
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieEntity

interface DbMovieRepository {
    fun setMovies(listMovieEntity: List<MovieEntity>): Completable

    fun getCategories(): Single<List<Category>>

    fun saveMoviesByCategories(categoriesWithMovies: List<MovieAndCategoryCrossRef>) : Completable

    fun setCategories(categories: List<Category>) : Completable

    fun getCategoryWithMoviesById(categoryId: String): Single<CategoryWithMovies>
}