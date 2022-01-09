package com.clothex.data.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Social(
    val _id: String,
    val icon: String,
    val regex: String,
    val title: String,
    val appendUrl: String?
) : Parcelable