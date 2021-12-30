package io.halan.data.domain.model


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("_id")
    val id: String,
    @SerializedName("main_image")
    val mainImage: MainImage,
    val price: Int,
    @SerializedName("sale_end_date")
    val saleEndDate: Long,
    @SerializedName("sale_price")
    val salePrice: Int,
    @SerializedName("sale_start_date")
    val saleStartDate: Long,
    val title: String
)