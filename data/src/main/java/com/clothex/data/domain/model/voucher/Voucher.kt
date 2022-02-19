package com.clothex.data.domain.model.voucher

import android.os.Parcelable
import com.clothex.data.domain.model.product.Media
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Voucher(
    @SerializedName("_id")
    val id: String,
    val logo: Media,
    val cover: Media,
    private val title: String,
    @SerializedName("title_ar")
    private val titleAr: String?,
    private val description: String,
    @SerializedName("description_ar")
    private val descriptionAr: String?,
    @SerializedName("expiry_date")
    val expiryDate: String,
    val shops: List<VoucherShop>,
    val code: String,
    val redeemed: Boolean = false
) : Parcelable {
    fun getTitle(isArabic: Boolean): String {
        return if (isArabic) titleAr ?: title
        else title
    }

    fun getDescription(isArabic: Boolean): String {
        return if (isArabic) descriptionAr ?: description
        else description
    }
}