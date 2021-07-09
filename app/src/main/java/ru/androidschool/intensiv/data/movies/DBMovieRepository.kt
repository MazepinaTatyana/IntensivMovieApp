package ru.androidschool.intensiv.data.movies

import android.content.Context
import io.reactivex.Single
import ru.androidschool.intensiv.database.MovieDatabase
import ru.androidschool.intensiv.model.db_movie_model.*

class DBMovieRepository(var context: Context) {

    fun setMovies(listMovie: List<Movie>) = MovieDatabase
        .getInstance(context)
        .getMovieDao()
        .setMovies(listMovie)

    fun getFavouriteMovies(): Single<List<FavouriteMovies>> = MovieDatabase
        .getInstance(context)
        .getFavouriteMovieDao()
        .getFavouriteMovies()

    fun getFavouriteMovieById(id: Int): Single<FavouriteMovies> = MovieDatabase
        .getInstance(context)
        .getFavouriteMovieDao()
        .getFavouriteMovieById(id)

    fun saveFavouriteMovie(movie: FavouriteMoviesEntity) = MovieDatabase
        .getInstance(context)
        .getFavouriteMovieDao()
        .saveFavouriteMovie(movie)

    fun deleteFavouriteMovie(movie: FavouriteMoviesEntity) = MovieDatabase
        .getInstance(context)
        .getFavouriteMovieDao()
        .deleteFavouriteMovie(movie)

    fun getCategories(): Single<List<Category>> = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .getCategories()

    fun saveMoviesByCategories(categoriesWithMovies: List<MovieAndCategoryCrossRef>) = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .saveMoviesByCategories(categoriesWithMovies)

    fun setCategories(categories: List<Category>) = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .setCategories(categories)

    fun getCategoryWithMoviesById(categoryId: String): Single<CategoryWithMovies> = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .getCategoryWithMoviesById(categoryId)
}
