package com.clothex.data.domain.model.department

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tag(
    @SerializedName("_id")
    val id: String,
    val title: String
) : Parcelable