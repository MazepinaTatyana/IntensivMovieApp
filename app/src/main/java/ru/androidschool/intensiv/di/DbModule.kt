package ru.androidschool.intensiv.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.androidschool.intensiv.data.db.database.MovieDatabase
import ru.androidschool.intensiv.data.repository.db_repository.DBMovieRepository

val databaseModule = module {
    single { DBMovieRepository(get(named("MovieDatabase"))) }
    single <MovieDatabase> { MovieDatabase.getInstance(androidContext()) }
}