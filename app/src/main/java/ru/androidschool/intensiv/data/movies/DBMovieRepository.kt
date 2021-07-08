package ru.androidschool.intensiv.data.movies

import android.content.Context
import androidx.room.Delete
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.database.MovieDatabase
import ru.androidschool.intensiv.model.db_movie_model.Category
import ru.androidschool.intensiv.model.db_movie_model.CategoryWithMovies
import ru.androidschool.intensiv.model.db_movie_model.Movie
import ru.androidschool.intensiv.model.db_movie_model.MovieAndCategoryCrossRef

class DBMovieRepository(var context: Context) {

    fun setMovies(listMovie: List<Movie>) = MovieDatabase
        .getInstance(context)
        .getMovieDao()
        .setMovies(listMovie)

    fun getFavouriteMovies(): Single<List<Int>> = MovieDatabase
        .getInstance(context)
        .getFavouriteMovieDao()
        .getFavouriteMovies()

    fun getFavouriteMovieById(id: Int): Single<MovieVo> = MovieDatabase
        .getInstance(context)
        .getFavouriteMovieDao()
        .getFavouriteMovieById(id)

    fun saveFavouriteMovie(movieId: Int) = MovieDatabase
        .getInstance(context)
        .getFavouriteMovieDao()
        .saveFavouriteMovie(movieId)

    fun deleteFavouriteMovie(movieId: Int) = MovieDatabase
        .getInstance(context)
        .getFavouriteMovieDao()
        .deleteFavouriteMovie(movieId)

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

    fun getCategoriesWithMovies(): Single<List<CategoryWithMovies>> = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .getCategoriesWithMovies()

    fun getMovieById(movieId: Int): Single<Movie> = MovieDatabase
        .getInstance(context)
        .getMovieDao()
        .getMovieById(movieId)
}
