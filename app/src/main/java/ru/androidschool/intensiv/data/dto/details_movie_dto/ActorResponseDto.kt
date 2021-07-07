package ru.androidschool.intensiv.data.dto.details_movie_dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ActorResponseDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cast")
    val actors: List<ActorDto>
)
