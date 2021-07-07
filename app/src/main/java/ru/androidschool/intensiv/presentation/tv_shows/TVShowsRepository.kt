package ru.androidschool.intensiv.presentation.tv_shows

import io.reactivex.Observable
import io.reactivex.Single
import ru.androidschool.intensiv.data.dto.MoviesApiResponseDto
import ru.androidschool.intensiv.data.network.MovieApiClient

object TVShowsRepository {

    fun getTVShows(): Single<MoviesApiResponseDto> = MovieApiClient.movieApiClient.getTvShows()
}
