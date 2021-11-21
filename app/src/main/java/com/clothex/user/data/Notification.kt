package com.clothex.user.data

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
data class Notification(
    val title: String,
    val description: String,
    val imageUrl: String,
    var isRead: Boolean
)
