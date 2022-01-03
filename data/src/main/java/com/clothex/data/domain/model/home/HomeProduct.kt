package com.clothex.data.domain.model.home

import com.clothex.data.domain.model.product.Media
import com.google.gson.annotations.SerializedName

data class HomeProduct(
    @SerializedName("_id")
    val id: String,
    @SerializedName("main_image")
    val mainImage: Media?,
    val price: Int,
    @SerializedName("sale_end_date")
    val saleEndDate: Long,
    @SerializedName("sale_price")
    val salePrice: Int?,
    @SerializedName("sale_start_date")
    val saleStartDate: Long,
    val title: String,
    val tag: String?,
    val tagColor: String?
)