package ru.androidschool.intensiv.presentation.base

enum class Status {
    SUCCESS,
    ERROR,
    LOADING;
    fun isLoading() = this == LOADING
}
