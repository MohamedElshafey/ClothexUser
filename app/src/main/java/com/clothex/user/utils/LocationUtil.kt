package com.clothex.user.utils

import android.annotation.SuppressLint
import android.location.Criteria
import android.location.Location
import android.location.LocationManager

@SuppressLint("MissingPermission")
fun getLastLocation(locationManager: LocationManager): Location? {
    val criteria = Criteria()
    return locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false)!!)
}