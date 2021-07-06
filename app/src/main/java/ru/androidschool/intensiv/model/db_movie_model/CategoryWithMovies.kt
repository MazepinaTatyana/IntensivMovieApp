package ru.androidschool.intensiv.model.db_movie_model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CategoryWithMovies(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "movieId",
        associateBy = Junction(
            MovieAndCategoryCrossRef::class
        )
    )
    val movies: List<Movie>
)