package ru.androidschool.intensiv.data.repository.db_repository

import android.content.Context
import io.reactivex.Single
import ru.androidschool.intensiv.data.db.database.MovieDatabase
import ru.androidschool.intensiv.data.db.model_db.entities_db.Category
import ru.androidschool.intensiv.data.db.model_db.CategoryWithMovies
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieFromDb
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieAndCategoryCrossRef

class DBMovieRepository(var context: Context) {

     fun getMovies(resString: String): Single<CategoryWithMovies> = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .getCategoryWithMoviesById(resString)

    fun setMovies(listMovieFromDb: List<MovieFromDb>) = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .setMovies(listMovieFromDb)

    fun deleteAllCat() = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .deleteAllCategories()

    fun getMovies(): Single<List<MovieFromDb>> = MovieDatabase
        .getInstance(context)
        .getMovieDao()
        .getMovies()

//     fun getNowPlayingMovies(): Observable<CategoryWithMovies> = MovieDatabase
//        .getInstance(context)
//        .getMoviesByCategoryDao()
//        .getCategoryWithMovies(R.string.recommended)
//
//     fun getUpcomingMovies(): Observable<CategoryWithMovies> = MovieDatabase
//        .getInstance(context)
//        .getMoviesByCategoryDao()
//        .getCategoryWithMovies(R.string.upcoming)

//    fun getAllCategories(): Observable<List<CategoryWithMovies>> = MovieDatabase
//        .getInstance(context)
//        .getMoviesByCategoryDao()
//        .getAllCategoriesWithMovies()

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


    fun getCategoryByMovieCategory(categoryTitle: Int) = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .getCategoryByMovieCategory(categoryTitle)

    fun getCategoryWithMoviesById(categoryId: String): Single<CategoryWithMovies> = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .getCategoryWithMoviesById(categoryId)

    fun getCategoriesWithMovies(): Single<List<CategoryWithMovies>> = MovieDatabase
        .getInstance(context)
        .getMoviesByCategoryDao()
        .getCategoriesWithMovies()
}
