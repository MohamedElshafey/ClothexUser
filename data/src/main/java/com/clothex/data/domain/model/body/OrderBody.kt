package com.clothex.data.domain.model.body

import com.google.gson.annotations.SerializedName

data class OrderBody(
    @SerializedName("my_items")
    val myItems: List<String>,
    @SerializedName("branch_id")
    val branchId: String,
    @SerializedName("shop_id")
    val shopId: String,
)
