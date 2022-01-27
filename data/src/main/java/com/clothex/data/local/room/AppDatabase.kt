package com.clothex.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.clothex.data.local.room.dao.SavedLocationDao
import com.clothex.data.local.room.entity.SavedLocation

@Database(entities = [SavedLocation::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun savedLocationDao(): SavedLocationDao
}