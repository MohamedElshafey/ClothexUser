package com.clothex.user.data.orders

import com.clothex.data.domain.model.my_item.MyItem
import com.clothex.user.data.Shop
import com.clothex.user.my_items.orders.OrderStatus

/**
 * Created by Mohamed Elshafey on 09/12/2021.
 */
data class Order(
    val orderId: String,
    val orderStatus: OrderStatus,
    val orderTimeStamp: Long,
    val shop: Shop,
    val bookedItems: List<MyItem>,
    val endTime: Long,
)

