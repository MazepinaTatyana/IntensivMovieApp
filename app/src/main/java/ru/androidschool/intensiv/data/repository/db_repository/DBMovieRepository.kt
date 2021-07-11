package ru.androidschool.intensiv.data.movies

import io.reactivex.Single
import ru.androidschool.intensiv.database.MovieDatabase
import ru.androidschool.intensiv.model.db_movie_model.*

class DBMovieRepository(var database: MovieDatabase) {

    fun setMovies(listMovie: List<Movie>) = database
        .getMovieDao()
        .setMovies(listMovie)

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
