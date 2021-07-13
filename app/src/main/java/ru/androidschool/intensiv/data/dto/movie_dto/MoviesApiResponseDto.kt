package ru.androidschool.intensiv.data.dto.movie_dto

import com.google.gson.annotations.SerializedName

data class MoviesApiResponseDto(
    @SerializedName("dates")
    var dates: DateDto? = null,
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("results")
    var results: List<MovieDto>? = null,
    @SerializedName("total_pages")
    var totalPages: Int? = null,
    @SerializedName("total_results")
    var totalResults: Int? = null
)
