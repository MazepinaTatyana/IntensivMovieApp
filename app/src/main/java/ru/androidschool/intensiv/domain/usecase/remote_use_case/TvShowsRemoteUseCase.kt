package ru.androidschool.intensiv.domain.usecase.remote_use_case

import io.reactivex.Single
import ru.androidschool.intensiv.data.vo.Movie
import ru.androidschool.intensiv.domain.repository.TvShowsRepository
import ru.androidschool.intensiv.domain.usecase.extensions.applySchedulers

class TvShowsRemoteUseCase(private val repository: TvShowsRepository) {

    fun getTVShows(): Single<List<Movie>> {
        return repository.getTVShows()
            .applySchedulers()
    }
}
