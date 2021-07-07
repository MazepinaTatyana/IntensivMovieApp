package ru.androidschool.intensiv.presentation.movie_details

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ActorResponse(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("cast")
    @Expose
    val actors: List<Actor>
)
