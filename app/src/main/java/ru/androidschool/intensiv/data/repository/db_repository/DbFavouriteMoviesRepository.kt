package ru.androidschool.intensiv.data.repository.db_repository

import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.androidschool.intensiv.data.db.database.MovieDatabase
import ru.androidschool.intensiv.data.db.model_db.entities_db.FavouriteMovies
import ru.androidschool.intensiv.data.db.model_db.entities_db.FavouriteMoviesEntity
import ru.androidschool.intensiv.domain.repository.DbFavouriteMovieRepository

class DbFavouriteMoviesRepository : DbFavouriteMovieRepository, KoinComponent {

    private val database: MovieDatabase by inject()

    override fun getFavouriteMovies(): Single<List<FavouriteMovies>> = database
        .getFavouriteMovieDao()
        .getFavouriteMovies()

    override fun getFavouriteMovieById(id: Int): Single<FavouriteMovies> = database
        .getFavouriteMovieDao()
        .getFavouriteMovieById(id)

    override fun saveFavouriteMovie(movie: FavouriteMoviesEntity) = database
        .getFavouriteMovieDao()
        .saveFavouriteMovie(movie)

    override fun deleteFavouriteMovie(movie: FavouriteMoviesEntity) = database
        .getFavouriteMovieDao()
        .deleteFavouriteMovie(movie)
}
