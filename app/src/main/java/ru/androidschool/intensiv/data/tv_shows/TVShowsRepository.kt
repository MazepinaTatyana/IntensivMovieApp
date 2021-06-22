package ru.androidschool.intensiv.data.tv_shows

import io.reactivex.Observable
import ru.androidschool.intensiv.model.movie_model.ApiResponse
import ru.androidschool.intensiv.network.MovieApiClient

object TVShowsRepository {

    fun getTVShows(): Observable<ApiResponse> = MovieApiClient.movieApiClient.getTvShows()
}
