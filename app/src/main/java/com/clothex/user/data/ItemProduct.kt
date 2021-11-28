package com.clothex.user.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemProduct(
    val _id: String?,
    val sub_product: ItemSubProduct?,
    val title: String
): Parcelable