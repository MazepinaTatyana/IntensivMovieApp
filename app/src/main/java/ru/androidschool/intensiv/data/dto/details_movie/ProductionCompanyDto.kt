package ru.androidschool.intensiv.data.dto.details_movie

import com.google.gson.annotations.SerializedName

data class ProductionCompanyDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("logo_path")
    val logoPath: Any? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("origin_country")
    val originCountry: String? = null
)
