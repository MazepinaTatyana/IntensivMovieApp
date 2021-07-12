package ru.androidschool.intensiv.domain.usecase.db_use_case

import io.reactivex.Single
import ru.androidschool.intensiv.data.db.model_db.entities_db.FavouriteMovies
import ru.androidschool.intensiv.data.db.model_db.entities_db.FavouriteMoviesEntity
import ru.androidschool.intensiv.data.repository.db_repository.DbFavouriteMovieRepository
import ru.androidschool.intensiv.domain.usecase.extensions.applySchedulers

class DbFavouriteMovieUseCase(private val dbFavouriteMovieRepository: DbFavouriteMovieRepository) {

    fun getFavouriteMovies(): Single<List<FavouriteMovies>> = dbFavouriteMovieRepository
        .getFavouriteMovies()
        .applySchedulers()

    fun getFavouriteMovieById(id: Int): Single<FavouriteMovies> = dbFavouriteMovieRepository
        .getFavouriteMovieById(id)
        .applySchedulers()

    fun saveFavouriteMovie(movie: FavouriteMoviesEntity) = dbFavouriteMovieRepository
        .saveFavouriteMovie(movie)
        .applySchedulers()

    fun deleteFavouriteMovie(movie: FavouriteMoviesEntity) = dbFavouriteMovieRepository
        .deleteFavouriteMovie(movie)
        .applySchedulers()
}
