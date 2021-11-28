package com.clothex.user.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Color(
    val _id: String?,
    var code: String?,
    var images: List<Media>?,
    var sizes: List<Size>?
): Parcelable