package ru.androidschool.intensiv.data

import ru.androidschool.intensiv.data.movies.MovieVo

interface BaseMapper<T> {
    fun toViewObject(dto: T): MovieVo

    fun toViewObject(list: Collection<T>): List<MovieVo> {
        val result = ArrayList<MovieVo>()
        list.mapTo(result) { toViewObject(it) }
        return result
    }
}
