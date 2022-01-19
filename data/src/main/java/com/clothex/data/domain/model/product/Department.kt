package com.clothex.data.domain.model.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
data class Department(
    val _id: String,
    var products: List<Product>,
    val title: String
) : Parcelable