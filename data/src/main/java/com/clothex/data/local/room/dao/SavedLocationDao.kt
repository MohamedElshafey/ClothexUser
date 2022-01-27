package com.clothex.data.local.room.dao

import androidx.room.*
import com.clothex.data.local.room.entity.SavedLocation

@Dao
interface SavedLocationDao {

    @Query("SELECT * FROM saved_location")
    fun getAll(): List<SavedLocation>

    @Query("SELECT * FROM saved_location WHERE selected = :selected")
    fun get(selected: Boolean): SavedLocation?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg locations: SavedLocation)

    @Delete
    fun delete(location: SavedLocation)

}