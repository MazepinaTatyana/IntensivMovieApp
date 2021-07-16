package ru.androidschool.intensiv.domain.usecase.remote_use_case

import ru.androidschool.intensiv.data.vo.Movie
import ru.androidschool.intensiv.domain.repository.TvShowsRepository

class TvShowsRemoteUseCase(private val repository: TvShowsRepository) {

    suspend fun getTVShows(): List<Movie> {
        return repository.getTVShows()
    }
}


