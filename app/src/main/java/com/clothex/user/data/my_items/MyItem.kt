package com.clothex.user.data.my_items

import android.os.Parcelable
import com.clothex.user.data.Shop
import kotlinx.android.parcel.Parcelize

/**
 * Created by Mohamed Elshafey on 08/12/2021.
 */
@Parcelize
data class MyItem(val shop: Shop, val myItems: List<MinimalProduct>) : Parcelable