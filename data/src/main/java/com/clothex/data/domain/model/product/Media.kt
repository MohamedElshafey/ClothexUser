package com.clothex.data.domain.model.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
data class Media(
    val _id: String?,
    val source: String,
    val thumbnail: String,
    val type: String
) : Parcelable