package ru.androidschool.intensiv.presentation.movie_details

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Actor(
    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("profile_path")
    @Expose
    val profilePath: String
)
