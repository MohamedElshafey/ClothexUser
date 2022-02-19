package com.clothex.data.domain.model.home

import android.os.Parcelable
import com.clothex.data.domain.model.product.Address
import com.clothex.data.domain.model.product.Media
import com.clothex.data.domain.model.product.WorkingHour
import com.google.gson.annotations.SerializedName

@kotlinx.parcelize.Parcelize
data class HomeShop(
    @SerializedName("_id")
    val id: String,
    val logo: Media?,
    private val name: String,
    @SerializedName("name_ar")
    private val nameAr: String?,
    val address: Address?,
    @SerializedName("working_hours")
    val workingHours: List<WorkingHour>?
) : Parcelable {

    fun getName(isArabic: Boolean): String {
        return if (isArabic) nameAr ?: name
        else name
    }

}