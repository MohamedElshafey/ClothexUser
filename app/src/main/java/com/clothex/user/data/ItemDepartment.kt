package com.clothex.user.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemDepartment(
    val _id: String?,
    val products: ItemProduct?,
    val title: String
): Parcelable