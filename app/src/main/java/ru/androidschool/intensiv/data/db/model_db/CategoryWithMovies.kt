package ru.androidschool.intensiv.data.db.model_db

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ru.androidschool.intensiv.data.db.model_db.entities_db.Category
import ru.androidschool.intensiv.data.db.model_db.entities_db.Movie
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieAndCategoryCrossRef

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
