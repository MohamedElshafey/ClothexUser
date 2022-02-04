package com.clothex.data.domain.model.department

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SubType(
    @SerializedName("_id")
    val id: String,
    val tags: List<Tag>,
    val title: String
) : Parcelable