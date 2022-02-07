package com.clothex.data.domain.model.notification

import com.clothex.data.domain.model.product.Media
import com.google.gson.annotations.SerializedName

data class Notification(
    @SerializedName("_id")
    val id: String,
    val action: String?,
    val createdAt: String,
    val description: String?,
    val logo: Media?,
    var read: Boolean,
    val title: String
)