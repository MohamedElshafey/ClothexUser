package com.clothex.user.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Size(
    val _id: String?,
    var available_branches: List<Branch>?,
    var title: String
) : Parcelable