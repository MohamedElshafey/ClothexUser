package com.clothex.user

import android.app.Application
import com.clothex.user.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Mohamed Elshafey on 30/12/2021.
 */
class AppClass : Application() {

    override fun onCreate() {
        super.onCreate()
        setUpKoin()
    }

    private fun setUpKoin() {
        startKoin {
            if (BuildConfig.DEBUG)
                androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@AppClass)
            modules(
                networkModule
            )
        }
    }
}