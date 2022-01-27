package com.clothex.user.di

import android.content.Context
import androidx.room.Room
import com.clothex.data.local.room.AppDatabase
import com.clothex.data.local.room.dao.SavedLocationDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val databaseModule = module {
    single { provideDatabase(context = androidContext()) }
    single { provideDao(get()) }
}

fun provideDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context,
        AppDatabase::class.java, "app_database"
    ).build()
}

fun provideDao(database: AppDatabase): SavedLocationDao {
    return database.savedLocationDao()
}

