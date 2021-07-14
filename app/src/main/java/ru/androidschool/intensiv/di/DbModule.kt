package ru.androidschool.intensiv.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.androidschool.intensiv.data.db.database.MovieDatabase

val databaseModule = module {
    single <MovieDatabase> { MovieDatabase.getInstance(androidContext()) }
}