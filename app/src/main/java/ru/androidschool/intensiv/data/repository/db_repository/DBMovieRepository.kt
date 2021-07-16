package ru.androidschool.intensiv.data.repository.db_repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.db.database.MovieDatabase
import ru.androidschool.intensiv.data.db.model_db.CategoryWithMovies
import ru.androidschool.intensiv.data.db.model_db.entities_db.Category
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieAndCategoryCrossRef
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieEntity
import ru.androidschool.intensiv.domain.repository.DbMovieRepository

class DBMovieRepository(private val database: MovieDatabase) : DbMovieRepository {

    override fun setMovies(listMovieEntity: List<MovieEntity>) = database
        .getMovieDao()
        .setMovies(listMovieEntity)

    override fun getCategories(): Single<List<Category>> = database
        .getMoviesByCategoryDao()
        .getCategories()

    override fun saveMoviesByCategories(categoriesWithMovies: List<MovieAndCategoryCrossRef>) = database
        .getMoviesByCategoryDao()
        .saveMoviesByCategories(categoriesWithMovies)

    override fun setCategories(categories: List<Category>) = database
        .getMoviesByCategoryDao()
        .setCategories(categories)

    override fun getCategoryWithMoviesById(categoryId: String): Single<CategoryWithMovies> = database
        .getMoviesByCategoryDao()
        .getCategoryWithMoviesById(categoryId)
}
