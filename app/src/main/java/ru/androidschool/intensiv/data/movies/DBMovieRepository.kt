package ru.androidschool.intensiv.data.movies

import android.content.Context
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Observable
import ru.androidschool.intensiv.database.MovieDatabase
import ru.androidschool.intensiv.model.db_movie_model.Category
import ru.androidschool.intensiv.model.db_movie_model.CategoryWithMovies
import ru.androidschool.intensiv.model.db_movie_model.Movie
import ru.androidschool.intensiv.ui.feed.MovieCategory

class DBMovieRepository(var context: Context): MovieRepository {

    override fun getPopularMovies(): Observable<CategoryWithMovies> = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .getCategoryWithMovies(MovieCategory.POPULAR)

    override fun getNowPlayingMovies(): Observable<CategoryWithMovies> = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .getCategoryWithMovies(MovieCategory.NOWPLAYING)

    override fun getUpcomingMovies(): Observable<CategoryWithMovies> = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .getCategoryWithMovies(MovieCategory.UPCOMING)

    fun getAllCategories(): Observable<List<CategoryWithMovies>> = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .getAllCategoriesWithMovies()

    fun getCategories(): Observable<List<Category>> = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .getCategories()

    fun saveMoviesByCategories(categoriesWithMovies: List<CategoryWithMovies>) = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .saveMoviesByCategories(categoriesWithMovies)

    fun setCategories(categories: List<Category>) = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .setCategories(categories)


    fun getCategoryByMovieCategory(movieCategory: MovieCategory) = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .getCategoryByMovieCategory(movieCategory)
}
