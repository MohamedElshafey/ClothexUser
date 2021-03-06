package com.clothex.data.domain.model.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
data class Branch(
    @SerializedName("_id")
    val id: String,
    var address: Address?,
//    var departments: List<Department>?,
    @SerializedName("inside_photos")
    var insidePhotos: List<Media>?,
    var name: String?,
    @SerializedName("outside_photos")
    var outsidePhotos: List<Media>?,
//    var phones: List<Phone>?,
    @SerializedName("working_hours")
    var workingHours: List<WorkingHour>?
) : Parcelable