package com.clothex.data.domain.model.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Phone(
    val _id: String?,
    val number: String,
    val title: String
) : Parcelable