package ru.androidschool.intensiv.domain.usecase.db_use_case

import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.androidschool.intensiv.data.db.model_db.entities_db.FavouriteMovies
import ru.androidschool.intensiv.data.db.model_db.entities_db.FavouriteMoviesEntity
import ru.androidschool.intensiv.domain.repository.DbFavouriteMovieRepository
import ru.androidschool.intensiv.domain.usecase.extensions.applySchedulers

class DbFavouriteMovieUseCase(private val dbFavouriteMoviesRepository: DbFavouriteMovieRepository) {

    fun getFavouriteMovies(): Single<List<FavouriteMovies>> = dbFavouriteMoviesRepository
        .getFavouriteMovies()
        .applySchedulers()

    fun getFavouriteMovieById(id: Int): Single<FavouriteMovies> = dbFavouriteMoviesRepository
        .getFavouriteMovieById(id)
        .applySchedulers()

    fun saveFavouriteMovie(movie: FavouriteMoviesEntity) = dbFavouriteMoviesRepository
        .saveFavouriteMovie(movie)
        .applySchedulers()

    fun deleteFavouriteMovie(movie: FavouriteMoviesEntity) = dbFavouriteMoviesRepository
        .deleteFavouriteMovie(movie)
        .applySchedulers()
}
