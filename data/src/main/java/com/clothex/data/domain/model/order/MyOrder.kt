package com.clothex.data.domain.model.order

import com.clothex.data.domain.model.my_item.MyItem
import com.clothex.data.domain.model.product.Branch
import com.clothex.data.domain.model.shop.Shop
import com.google.gson.annotations.SerializedName

data class MyOrder(
    @SerializedName("_id")
    val id: String,
    val branch: Branch,
    @SerializedName("my_items")
    val myItems: List<MyItem>,
    val shop: Shop,
    val state: OrderState,
    @SerializedName("order_time_stamp")
    val orderTimeStamp: String?,
    @SerializedName("end_time")
    val endTime: String?,
    @SerializedName("createdAt")
    val placedOn: String?,
    @SerializedName("order_id")
    val orderId: String,
    @SerializedName("booked_items")
    val bookedItems: List<String>
)