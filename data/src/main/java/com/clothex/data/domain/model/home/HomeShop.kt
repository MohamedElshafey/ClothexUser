package com.clothex.data.domain.model.home

import android.os.Parcelable
import com.clothex.data.domain.model.product.Address
import com.clothex.data.domain.model.product.Media
import com.clothex.data.domain.model.product.WorkingHour
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeShop(
    @SerializedName("_id")
    val id: String,
    val logo: Media?,
    val name: String,
    val name_ar: String,
    val address: Address,
    @SerializedName("working_hours")
    val workingHours: List<WorkingHour>?
) : Parcelable