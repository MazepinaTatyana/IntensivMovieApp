package ru.androidschool.intensiv.data.movies

import android.content.Context
import io.reactivex.Observable
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.database.MovieDatabase
import ru.androidschool.intensiv.model.db_movie_model.Category
import ru.androidschool.intensiv.model.db_movie_model.CategoryWithMovies
import ru.androidschool.intensiv.model.db_movie_model.MovieAndCategoryCrossRef

class DBMovieRepository(var context: Context): MovieRepository {

    override fun getPopularMovies(): Observable<CategoryWithMovies> = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .getCategoryWithMovies(R.string.popular)

    override fun getNowPlayingMovies(): Observable<CategoryWithMovies> = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .getCategoryWithMovies(R.string.recommended)

    override fun getUpcomingMovies(): Observable<CategoryWithMovies> = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .getCategoryWithMovies(R.string.upcoming)

//    fun getAllCategories(): Observable<List<CategoryWithMovies>> = MovieDatabase
//        .getInstance(context)
//        .getMoviesByCategoryDao()
//        .getAllCategoriesWithMovies()

    fun getCategories(): Observable<List<Category>> = MovieDatabase
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


    fun getCategoryByMovieCategory(categoryTitle: Int) = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .getCategoryByMovieCategory(categoryTitle)
}
