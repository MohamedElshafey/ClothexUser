package com.clothex.user.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WorkingHour(
    val _id: String,
    val enabled: Boolean,
    val from: String,
    val title: String,
    val to: String
) : Parcelable