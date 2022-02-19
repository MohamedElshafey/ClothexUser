package com.clothex.data.domain.model.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@kotlinx.parcelize.Parcelize
data class Address(
    val _id: String?,
    val location: Location?,
    private val name: String,
    @SerializedName("name_ar")
    private val nameAr: String?
) : Parcelable {
    fun getName(isArabic: Boolean): String {
        return if (isArabic) nameAr ?: name
        else name
    }
}