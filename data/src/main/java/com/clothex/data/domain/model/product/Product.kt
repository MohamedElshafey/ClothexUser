package com.clothex.data.domain.model.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    @SerializedName("_id")
    val id: String,
    val price: Int,
    @SerializedName("sale_end_date")
    val saleEndDate: Long,
    @SerializedName("sale_price")
    val salePrice: Int?,
    @SerializedName("sale_start_date")
    val saleStartDate: Long,
    val title: String,
    val tag: String?,
    val tagColor: String?,
    val available: Boolean?,
    val colors: List<Color>?,
    val department: ItemDepartment?,
    val sku: String?,
    val shop: ProductShop?
) : Parcelable