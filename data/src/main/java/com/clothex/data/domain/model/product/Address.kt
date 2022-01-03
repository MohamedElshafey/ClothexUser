package com.clothex.data.domain.model.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
    val _id: String?,
    var location: Location?,
    var name: String
) : Parcelable