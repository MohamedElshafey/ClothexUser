package com.clothex.data.domain.model.department

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Department(
    @SerializedName("id")
    val id: String,
    val title: String,
    @SerializedName("title_ar")
    val titleAr: String,
    val types: List<Type>
) : Parcelable