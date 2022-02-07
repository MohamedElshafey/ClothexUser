package com.clothex.data.remote.repository.notification

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.repository.notification.IReadNotificationRepository
import com.clothex.data.remote.api.NotificationApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class ReadNotificationRepository(private val apiService: NotificationApiService) :
    IReadNotificationRepository {
    override suspend fun readNotification(notificationId: String): Flow<SimpleResponse> = flow {
        emit(apiService.readNotification(notificationId))
    }
}