package com.clothex.user.home.notification.adapter

import com.clothex.data.domain.model.notification.Notification
import com.clothex.user.base.BaseLanguageViewModel

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class NotificationItemViewModel(notification: Notification) : BaseLanguageViewModel() {

    val imageUrl = notification.logo?.source

    val title = notification.getTitle(isArabic())

    val description = notification.description

}