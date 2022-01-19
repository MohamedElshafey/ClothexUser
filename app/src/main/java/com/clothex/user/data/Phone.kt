package com.clothex.user.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
data class Phone(
    val _id: String?,
    val number: String,
    val title: String
) : Parcelable