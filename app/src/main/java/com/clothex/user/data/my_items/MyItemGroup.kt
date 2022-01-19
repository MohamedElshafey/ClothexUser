package com.clothex.user.data.my_items

import android.os.Parcelable
import com.clothex.data.domain.model.my_item.MyItem
import com.clothex.data.domain.model.product.Branch
import com.clothex.data.domain.model.shop.Shop

/**
 * Created by Mohamed Elshafey on 08/12/2021.
 */
@kotlinx.parcelize.Parcelize
data class MyItemGroup(val shop: Shop, val branch: Branch, val myItems: List<MyItem>) : Parcelable