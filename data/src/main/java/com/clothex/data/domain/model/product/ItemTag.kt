package com.clothex.data.domain.model.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemTag(
    val _id: String,
    val title: String
) : Parcelable