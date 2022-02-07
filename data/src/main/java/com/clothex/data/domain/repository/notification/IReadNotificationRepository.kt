package com.clothex.data.domain.repository.notification

import com.clothex.data.domain.model.SimpleResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface IReadNotificationRepository {
    suspend fun readNotification(notificationId: String): Flow<SimpleResponse>
}