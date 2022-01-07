package com.clothex.data.domain.model.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val _id: String?,
    var coordinates: List<Double>,
) : Parcelable