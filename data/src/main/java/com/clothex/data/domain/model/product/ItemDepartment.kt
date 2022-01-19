package com.clothex.data.domain.model.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
data class ItemDepartment(
    val _id: String?,
    val products: ItemProduct?,
    val title: String
): Parcelable