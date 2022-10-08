package com.clothex.data.local.room.dao

import androidx.room.*
import com.clothex.data.local.room.entity.SavedLocation
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedLocationDao {

    @Query("SELECT * FROM saved_location")
    fun getAll(): List<SavedLocation>

    @Query("SELECT * FROM saved_location WHERE selected = :selected")
    fun get(selected: Boolean): Flow<SavedLocation?>

    @Query("SELECT * FROM saved_location WHERE selected = :selected")
    fun getSavedLocation(selected: Boolean): SavedLocation?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg locations: SavedLocation)

    @Delete
    fun delete(location: SavedLocation)

}