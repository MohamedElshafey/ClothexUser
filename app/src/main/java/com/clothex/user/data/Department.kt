package com.clothex.user.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Department(
    val _id: String,
    var products: List<Product>,
    val title: String
) : Parcelable