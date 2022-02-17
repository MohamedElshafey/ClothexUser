package com.clothex.data.domain.model.department

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Type(
    @SerializedName("_id")
    val id: String,
    val sub_type: List<SubType>,
    private val title: String,
    @SerializedName("title_ar")
    private val titleAr: String?
) : Parcelable {
    fun getTitle(isArabic: Boolean): String {
        return if (isArabic) titleAr ?: title
        else title
    }
}