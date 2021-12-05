package com.clothex.user.data.my_items

import android.os.Parcelable
import com.clothex.user.data.Media
import kotlinx.android.parcel.Parcelize

/**
 * Created by Mohamed Elshafey on 05/12/2021.
 */

@Parcelize
data class MyItem(
    val title: String,
    val colorCode: String,
    val sizeName: String,
    val quantity: Int,
    val image: Media?,
    val price: Int
) : Parcelable
