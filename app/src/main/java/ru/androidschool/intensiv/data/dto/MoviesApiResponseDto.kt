package ru.androidschool.intensiv.data.dto

import com.google.gson.annotations.SerializedName

data class MoviesApiResponseDto(
    @SerializedName("dates")
    var dates: DateDto? = null,
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    var results: List<MovieDto>,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("total_results")
    var totalResults: Int
)
