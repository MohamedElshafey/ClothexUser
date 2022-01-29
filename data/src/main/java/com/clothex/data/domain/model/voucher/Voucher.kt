package com.clothex.data.domain.model.voucher

import android.os.Parcelable
import com.clothex.data.domain.model.product.Media
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Voucher(
    @SerializedName("_id")
    val id: String,
    val logo: Media,
    val cover: Media,
    val title: String,
    val description: String,
    @SerializedName("expiry_date")
    val expiryDate: Date,
    val shops: List<VoucherShop>,
    val code: String,
    val redeemed: Boolean = false
) : Parcelable