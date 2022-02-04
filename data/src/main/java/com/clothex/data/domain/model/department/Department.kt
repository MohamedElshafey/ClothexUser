package com.clothex.data.domain.model.department

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Department(
    @SerializedName("_id")
    val id: String,
    val title: String,
    val types: List<Type>
) : Parcelable