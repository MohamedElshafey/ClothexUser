package com.clothex.data.domain.model.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemSubProduct(
    val _id: String?,
    val tags: List<ItemTag>?,
    val title: String
) : Parcelable