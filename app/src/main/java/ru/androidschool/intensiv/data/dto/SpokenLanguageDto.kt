package ru.androidschool.intensiv.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SpokenLanguageDto(
    @SerializedName("english_name")
    @Expose
    val englishName: String? = null,
    @SerializedName("iso_639_1")
    @Expose
    val iso6391: String? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null
)
