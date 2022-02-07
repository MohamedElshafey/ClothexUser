package com.clothex.user.home.notification.adapter

import androidx.lifecycle.ViewModel
import com.clothex.data.domain.model.notification.Notification

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class NotificationItemViewModel(notification: Notification) :
    ViewModel() {

    val imageUrl = notification.logo?.source

    val title = notification.title

    val description = notification.description

}