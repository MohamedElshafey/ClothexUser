package com.clothex.data.domain.model.body

data class MyItemBody(
    val branch_id: String,
    val color_code: String,
    val customer_id: String,
    val product_id: String,
    val shop_id: String,
    val quantity: Int,
    val size_name: String
)