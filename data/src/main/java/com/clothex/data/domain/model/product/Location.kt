package com.clothex.data.domain.model.product

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class Location(
    val _id: String?,
    var coordinates: List<Double>,//Lat [1] Lng[0]
) : Parcelable