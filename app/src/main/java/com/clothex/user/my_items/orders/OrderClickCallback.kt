package com.clothex.user.my_items.orders

import com.clothex.data.domain.model.order.MyOrder

interface OrderClickCallback {
    fun onGetDirectionClicked(order: MyOrder)
    fun onOrderSelected(order: MyOrder)
}