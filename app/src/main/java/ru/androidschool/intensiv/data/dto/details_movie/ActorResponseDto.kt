package ru.androidschool.intensiv.data.dto.details_movie

import com.google.gson.annotations.SerializedName

data class ActorResponseDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("cast")
    val actors: List<ActorDto>? = null
)
