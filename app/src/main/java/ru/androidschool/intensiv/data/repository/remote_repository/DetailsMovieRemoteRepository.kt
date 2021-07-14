package ru.androidschool.intensiv.data.repository.remote_repository

import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.androidschool.intensiv.data.mappers.MapperToDetailsMovie
import ru.androidschool.intensiv.data.network.MovieApiClient
import ru.androidschool.intensiv.data.network.MovieApiInterface
import ru.androidschool.intensiv.data.vo.DetailsMovie
import ru.androidschool.intensiv.domain.repository.DetailsMovieRepository

class DetailsMovieRemoteRepository : DetailsMovieRepository, KoinComponent {

    private val movieApiClient: MovieApiInterface by inject()

    override fun getDetailsMovieById(movieId: Int): Single<DetailsMovie> {
        return movieApiClient.getDetailsMovieById(movieId)
            .map { MapperToDetailsMovie.convertToDetailsMovie(it) }
    }
}
