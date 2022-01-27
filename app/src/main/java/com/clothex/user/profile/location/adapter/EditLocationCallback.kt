package com.clothex.user.profile.location.adapter

import com.clothex.data.local.room.entity.SavedLocation

interface EditLocationCallback {

    fun onItemDeleted(savedLocation: SavedLocation)

}