package com.clothex.data.domain.repository.notification

import com.clothex.data.domain.model.notification.Notification
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface IGetNotificationRepository {
    suspend fun getNotifications(): Flow<List<Notification>>
}