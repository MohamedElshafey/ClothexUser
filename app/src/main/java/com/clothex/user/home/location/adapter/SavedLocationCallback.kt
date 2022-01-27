package com.clothex.user.home.location.adapter

import com.clothex.data.local.room.entity.SavedLocation

interface SavedLocationCallback {

    fun onItemSelected(savedLocation: SavedLocation)

    fun onItemUnSelected(savedLocation: SavedLocation)

}