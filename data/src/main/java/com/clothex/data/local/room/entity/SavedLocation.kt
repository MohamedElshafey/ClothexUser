package com.clothex.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_location")
data class SavedLocation(
    val title: String,
    @ColumnInfo(name = "sub_title") val subTitle: String,
    var selected: Boolean,
    val latitude: Double,
    val longitude: Double
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}
