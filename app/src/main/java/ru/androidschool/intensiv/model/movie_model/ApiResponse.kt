package ru.androidschool.intensiv.model.movie_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("dates")
    @Expose
    var dates: Dates? = null,
    @SerializedName("page")
    @Expose
    var page: Int,
    @SerializedName("results")
    @Expose
    var results: List<ResultApi>,
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int,
    @SerializedName("total_results")
    @Expose
    var totalResults: Int
)
