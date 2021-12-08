package com.clothex.user.data.my_items

import com.clothex.user.data.Shop

/**
 * Created by Mohamed Elshafey on 08/12/2021.
 */
data class MyItem(val shop: Shop, val myItems: List<MinimalProduct>)