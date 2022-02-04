package com.clothex.data.domain.model.department

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Type(
    @SerializedName("_id")
    val id: String,
    val sub_type: List<SubType>,
    val title: String
) : Parcelable