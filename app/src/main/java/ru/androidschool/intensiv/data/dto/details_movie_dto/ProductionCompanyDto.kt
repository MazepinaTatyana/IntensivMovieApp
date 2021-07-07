package ru.androidschool.intensiv.data.dto.details_movie_dto

import com.google.gson.annotations.SerializedName

data class ProductionCompanyDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: Any,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)
