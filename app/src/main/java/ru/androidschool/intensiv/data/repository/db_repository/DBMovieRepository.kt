package ru.androidschool.intensiv.data.repository.db_repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.db.model_db.CategoryWithMovies
import ru.androidschool.intensiv.data.db.model_db.entities_db.*
import ru.androidschool.intensiv.data.db.database.MovieDatabase

class DBMovieRepository(var database: MovieDatabase) {

    fun setMovies(listMovieDb: List<MovieDb>) = database
        .getMovieDao()
        .setMovies(listMovieDb)

    fun getFavouriteMovies(): Single<List<FavouriteMovies>> = database
        .getFavouriteMovieDao()
        .getFavouriteMovies()

    fun getFavouriteMovieById(id: Int): Single<FavouriteMovies> = database
        .getFavouriteMovieDao()
        .getFavouriteMovieById(id)

    fun saveFavouriteMovie(movie: FavouriteMoviesEntity) = database
        .getFavouriteMovieDao()
        .saveFavouriteMovie(movie)

    fun deleteFavouriteMovie(movie: FavouriteMoviesEntity) = database
        .getFavouriteMovieDao()
        .deleteFavouriteMovie(movie)

    fun getCategories(): Single<List<Category>> = database
        .getMoviesByCategoryDao()
        .getCategories()

    fun saveMoviesByCategories(categoriesWithMovies: List<MovieAndCategoryCrossRef>) = database
        .getMoviesByCategoryDao()
        .saveMoviesByCategories(categoriesWithMovies)

    fun setCategories(categories: List<Category>) = database
        .getMoviesByCategoryDao()
        .setCategories(categories)

    fun getCategoryWithMoviesById(categoryId: String): Single<CategoryWithMovies> = database
        .getMoviesByCategoryDao()
        .getCategoryWithMoviesById(categoryId)
}
