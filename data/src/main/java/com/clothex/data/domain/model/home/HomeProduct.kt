package com.clothex.data.domain.model.home

import android.os.Parcelable
import com.clothex.data.domain.model.product.Media
import com.google.gson.annotations.SerializedName

@kotlinx.parcelize.Parcelize
data class HomeProduct(
    @SerializedName("_id")
    val id: String,
    @SerializedName("main_image")
    val mainImage: Media?,
    val price: Int,
    @SerializedName("sale_end_date")
    val saleEndDate: String?,
    @SerializedName("sale_price")
    val salePrice: Int?,
    @SerializedName("sale_start_date")
    val saleStartDate: String?,
    private val title: String,
    @SerializedName("title_ar")
    private val titleAr: String?,
    val tag: String?,
    val tagColor: String?
) : Parcelable {
    fun getTitle(isArabic: Boolean): String {
        return if (isArabic) titleAr ?: title
        else title
    }
}