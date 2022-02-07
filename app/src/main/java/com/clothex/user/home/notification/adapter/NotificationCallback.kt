package com.clothex.user.home.notification.adapter

import com.clothex.data.domain.model.notification.Notification

interface NotificationCallback {
    fun onItemClicked(notification: Notification)
}