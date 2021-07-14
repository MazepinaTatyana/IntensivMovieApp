package ru.androidschool.intensiv.domain.usecase.db_use_case

import io.reactivex.Single
import ru.androidschool.intensiv.data.db.model_db.CategoryWithMovies
import ru.androidschool.intensiv.data.db.model_db.entities_db.Category
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieAndCategoryCrossRef
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieEntity
import ru.androidschool.intensiv.domain.repository.DbMovieRepository
import ru.androidschool.intensiv.domain.usecase.extensions.applySchedulers

class DbMovieUseCase(private val dbRepository: DbMovieRepository) {

    fun setMovies(listMovieEntity: List<MovieEntity>) = dbRepository.setMovies(listMovieEntity)
        .applySchedulers()

    fun getCategories(): Single<List<Category>> = dbRepository.getCategories()
        .applySchedulers()

    fun saveMoviesByCategories(categoriesWithMovies: List<MovieAndCategoryCrossRef>) = dbRepository
        .saveMoviesByCategories(categoriesWithMovies)
        .applySchedulers()

    fun setCategories(categories: List<Category>) = dbRepository.setCategories(categories)
        .applySchedulers()

    fun getCategoryWithMoviesById(categoryId: String): Single<CategoryWithMovies> = dbRepository
        .getCategoryWithMoviesById(categoryId)
        .applySchedulers()
}
