package com.clothex.data.domain.model.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
data class ItemProduct(
    val _id: String?,
    val sub_product: ItemSubProduct?,
    val title: String
): Parcelable