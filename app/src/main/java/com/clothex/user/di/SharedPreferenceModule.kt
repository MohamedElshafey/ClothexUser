package com.clothex.user.di

import android.content.Context.MODE_PRIVATE
import com.clothex.data.local.LocalDataSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */


val localDataSourceModule = module {

    single {
        androidApplication().applicationContext.getSharedPreferences(
            "LOCAL_PREF",
            MODE_PRIVATE
        )
    }
    single { LocalDataSourceImpl(get()) }
}