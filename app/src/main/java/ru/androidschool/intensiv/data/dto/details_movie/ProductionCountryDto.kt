package ru.androidschool.intensiv.data.dto.details_movie

import com.google.gson.annotations.SerializedName

data class ProductionCountryDto(
    @SerializedName("iso_3166_1")
    val iso31661: String? = null,
    @SerializedName("name")
    val name: String? = null
)
