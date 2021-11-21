package com.clothex.user.home.notification.adapter

import androidx.lifecycle.ViewModel
import com.clothex.user.data.Notification

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class NotificationItemViewModel(private val notification: Notification) : ViewModel() {

    val imageUrl = notification.imageUrl

    val title = notification.title

    val description = notification.description

}