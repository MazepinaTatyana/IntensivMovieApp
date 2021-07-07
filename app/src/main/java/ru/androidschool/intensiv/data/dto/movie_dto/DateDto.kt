package ru.androidschool.intensiv.data.dto.movie_dto

import com.google.gson.annotations.SerializedName

data class DateDto(
    @SerializedName("maximum")
    private val maximum: String? = null,
    @SerializedName("minimum")
    private val minimum: String? = null
)
