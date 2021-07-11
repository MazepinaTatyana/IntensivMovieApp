package ru.androidschool.intensiv.data.tv_shows

import io.reactivex.Single
import ru.androidschool.intensiv.model.movie_model.ApiResponse
import ru.androidschool.intensiv.network.MovieApiClient

object TVShowsRepository {

    fun getTVShows(): Single<ApiResponse> = MovieApiClient.movieApiClient.getTvShows()
}
