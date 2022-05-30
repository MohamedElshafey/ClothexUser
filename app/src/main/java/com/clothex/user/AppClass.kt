package com.clothex.user

import android.app.Application
import com.clothex.data.domain.usecases.local.ClearSessionUseCase
import com.clothex.user.di.*
import com.facebook.FacebookSdk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named

/**
 * Created by Mohamed Elshafey on 30/12/2021.
 */
class AppClass : Application() {

    private val clearSessionUseCase: ClearSessionUseCase by inject(named("clear_session"))
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        setUpKoin()
        applicationScope.launch {
            clearSessionUseCase.invoke(Unit)
        }
        FacebookSdk.sdkInitialize(applicationContext);
//        AppEventsLogger.activateApp(this);
    }

    private fun setUpKoin() {
        startKoin {
            if (BuildConfig.DEBUG)
                androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@AppClass)
            modules(
                sharedPrefModule,
                networkModule,
                remoteDataSourceModule,
                logDataSourceModule,
                useCaseModule,
                viewModelsModule,
                databaseModule
            )
        }
    }


}