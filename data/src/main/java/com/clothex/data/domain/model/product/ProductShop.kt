package com.clothex.data.domain.model.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@kotlinx.parcelize.Parcelize
data class ProductShop(
    @SerializedName("_id")
    val id: String,
    val name: String,
    @SerializedName("name_ar")
    val arabicName: String,
    val logo: Media,
    @SerializedName("has_book")
    val hasBook: Boolean
) : Parcelable