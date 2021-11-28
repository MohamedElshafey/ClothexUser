package com.clothex.user.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Media(
    val _id: String?,
    val source: String,
    val thumbnail: String,
    val type: String
) : Parcelable