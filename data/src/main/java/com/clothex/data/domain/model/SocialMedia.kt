package com.clothex.data.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@kotlinx.parcelize.Parcelize
data class SocialMedia(
    val _id: String,
    val social: Social,
    val value: String
):Parcelable