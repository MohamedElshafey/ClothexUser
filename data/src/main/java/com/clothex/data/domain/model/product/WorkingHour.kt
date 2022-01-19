package com.clothex.data.domain.model.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
data class WorkingHour(
    val _id: String,
    val enabled: Boolean,
    val from: String,
    val title: String,
    val to: String
) : Parcelable