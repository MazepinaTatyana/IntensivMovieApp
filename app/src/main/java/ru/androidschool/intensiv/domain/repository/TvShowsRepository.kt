package ru.androidschool.intensiv.domain.repository

import ru.androidschool.intensiv.data.vo.Movie

interface TvShowsRepository {
    suspend fun getTVShows(): List<Movie>
}
