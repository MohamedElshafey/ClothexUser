package com.clothex.user.data.my_items

import android.os.Parcelable
import com.clothex.data.domain.model.home.HomeShop
import com.clothex.data.domain.model.my_item.MyItem
import com.clothex.data.domain.model.product.Branch

/**
 * Created by Mohamed Elshafey on 08/12/2021.
 */
@kotlinx.parcelize.Parcelize
data class MyItemGroup(val shop: HomeShop, val branch: Branch, val myItems: List<MyItem>) : Parcelable