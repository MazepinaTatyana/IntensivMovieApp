package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.constants.RATING

object Rating {
    fun calculateRating(rating: Double?): Double {
        return rating?.let { it / RATING}  ?: 0.0
    }
}
