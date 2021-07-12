package ru.androidschool.intensiv.presentation.tvshows

import ru.androidschool.intensiv.data.vo.Movie

interface TvShowView {
    fun showTvShows(tvShows: List<Movie>)
    fun showProgressBar()
    fun hideProgressBar()
}
