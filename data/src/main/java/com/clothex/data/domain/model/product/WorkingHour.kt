package com.clothex.data.domain.model.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@kotlinx.parcelize.Parcelize
data class WorkingHour(
    val _id: String,
    val enabled: Boolean,
    val from: String,
    private val title: String,
    @SerializedName("title_ar")
    private val titleAr: String?,
    val to: String
) : Parcelable {
    fun getTitle(isArabic: Boolean): String {
        return if (isArabic) titleAr ?: title
        else title
    }
}