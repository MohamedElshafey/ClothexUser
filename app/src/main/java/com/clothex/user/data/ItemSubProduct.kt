package com.clothex.user.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemSubProduct(
    val _id: String?,
    val tags: List<Tag>?,
    val title: String
): Parcelable