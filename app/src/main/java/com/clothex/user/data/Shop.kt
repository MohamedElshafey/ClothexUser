package com.clothex.user.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
@Parcelize
data class Shop(
    val logoUrl: String,
    val name: String,
    val addressName: String,
    val workingHour: String
) : Parcelable