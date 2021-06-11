package ru.androidschool.intensiv.model.tv_show_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TvShowRespounse(

    @SerializedName("page")
    @Expose
    val page: Int,

    @SerializedName("results")
    @Expose
    val tvShows: List<TvShowModel>? = null,

    @SerializedName("total_pages")
    @Expose
    val totalPages: Int,

    @SerializedName("total_results")
    @Expose
    val totalResults: Int
)
