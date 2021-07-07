package ru.androidschool.intensiv.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductionCountryDto(
    @SerializedName("iso_3166_1")
    @Expose
    val iso31661: String? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null
)
