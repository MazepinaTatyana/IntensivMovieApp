package ru.androidschool.intensiv.model.movie_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultFeedMovies(

    @SerializedName("popularMovies")
    @Expose
    var popularMovies: List<ResultApi>,

    @SerializedName("nowPlayingMovies")
    @Expose
    var nowPlayingMovies: List<ResultApi>,

    @SerializedName("upcomingMovies")
    @Expose
    var upcomingMovies: List<ResultApi>
)
