package ru.androidschool.intensiv.data.dto.details_movie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ActorDto(
    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("profile_path")
    @Expose
    val profilePath: String? = null
)
