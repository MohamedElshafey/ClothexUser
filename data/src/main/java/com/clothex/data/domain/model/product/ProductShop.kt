package com.clothex.data.domain.model.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductShop(
    val name: String,
    @SerializedName("name_ar")
    val arabicName: String,
    val logo: Media
) : Parcelable