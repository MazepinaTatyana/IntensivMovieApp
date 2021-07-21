package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.vo.Movie

interface BaseMapper<T> {
    fun toViewObject(dto: T): Movie

    fun toViewObject(list: Collection<T>): List<Movie> {
        val result = ArrayList<Movie>()
        list.mapTo(result) { toViewObject(it) }
        return result
    }
}
