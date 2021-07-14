package ru.androidschool.intensiv

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.androidschool.intensiv.di.apiModule
import ru.androidschool.intensiv.di.databaseModule
import ru.androidschool.intensiv.di.domainModule
import ru.androidschool.intensiv.di.presentationModule
import timber.log.Timber

class MovieFinderApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDebugTools()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MovieFinderApp)
            modules(databaseModule, presentationModule, domainModule, apiModule)
        }
    }

    private fun initDebugTools() {
        if (BuildConfig.DEBUG) {
            initTimber()
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        var instance: MovieFinderApp? = null
            private set
    }
}
