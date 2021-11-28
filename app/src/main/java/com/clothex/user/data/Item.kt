package com.clothex.shop.core.models

import android.os.Parcelable
import com.clothex.user.data.Color
import com.clothex.user.data.ItemDepartment
import com.clothex.user.data.Media
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val _id: String?,
    val available: Boolean?,
    val colors: List<Color>?,
    val department: ItemDepartment?,
    val images: List<Media>?,
    val price: Int?,
    val tag: String?,
    val tagColor: String?,
    val title: String?,
    val sale_price: Int?,
    val sale_start_date: Long?,
    val sale_end_date: Long?,
    val sku: String?
) : Parcelable