package ru.androidschool.intensiv.domain.repository

import io.reactivex.Single
import ru.androidschool.intensiv.data.vo.Movie

interface TvShowsRepository {
    fun getTVShows(): Single<List<Movie>>
}
