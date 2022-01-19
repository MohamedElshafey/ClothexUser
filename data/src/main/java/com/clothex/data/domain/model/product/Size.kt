package com.clothex.data.domain.model.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
data class Size(
    val _id: String?,
    var available_branches: List<Branch>?,
    var title: String
) : Parcelable