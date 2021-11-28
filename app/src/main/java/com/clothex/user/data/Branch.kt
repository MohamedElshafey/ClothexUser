package com.clothex.user.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Branch(
    val _id: String?,
    var address: Address?,
    var departments: List<Department>?,
    var inside_photos: List<Media>?,
    var name: String?,
    var outside_photos: List<Media>?,
    var phones: List<Phone>?,
    var working_hours: List<WorkingHour>?
) : Parcelable