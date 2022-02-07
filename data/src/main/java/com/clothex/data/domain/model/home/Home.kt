package com.clothex.data.domain.model.home

import com.google.gson.annotations.SerializedName

data class Home(
    val products: List<HomeProduct>,
    val shops: List<HomeShop>,
    @SerializedName("notification_count")
    val notificationCount: Int
)