package ru.androidschool.intensiv.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.androidschool.intensiv.data.repository.remote_repository.ActorsMovieRemoteRepository
import ru.androidschool.intensiv.data.repository.remote_repository.DetailsMovieRemoteRepository
import ru.androidschool.intensiv.domain.repository.ActorsMovieRepository
import ru.androidschool.intensiv.domain.repository.DetailsMovieRepository
import ru.androidschool.intensiv.domain.usecase.db_use_case.DbMovieUseCase
import ru.androidschool.intensiv.domain.usecase.remote_use_case.ActorsMovieRemoteUseCase
import ru.androidschool.intensiv.domain.usecase.remote_use_case.DetailsMovieRemoteUseCase

val domainModule = module {
    single { DetailsMovieRemoteUseCase(get(named("DetailsMovieRemoteRepository"))) }
    single<DetailsMovieRepository> { DetailsMovieRemoteRepository() }
    single { ActorsMovieRemoteUseCase(get(named("ActorsMovieRemoteRepository"))) }
    single<ActorsMovieRepository> { ActorsMovieRemoteRepository() }
    single { DbMovieUseCase(get(named("DbMovieRepository"))) }
}