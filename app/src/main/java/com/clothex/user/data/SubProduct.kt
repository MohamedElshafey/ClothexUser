package com.clothex.user.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SubProduct(
    val _id: String,
    val tags: List<Tag> = emptyList(),
    val title: String,
) : Parcelable