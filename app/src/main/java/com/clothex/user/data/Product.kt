package com.clothex.user.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val _id: String,
    val subProduct: List<SubProduct>?,
    val title: String
) : Parcelable