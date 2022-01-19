package com.clothex.data.domain.model.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
data class Color(
    val _id: String?,
    var code: String?,
    var images: List<Media>?,
    var sizes: List<Size>?
): Parcelable