package com.clothex.user.data.orders

/**
 * Created by Mohamed Elshafey on 09/12/2021.
 */
enum class OrderStatus(val value: Int) {
    PENDING(0), ACTIVE(1), REJECT(2)
}