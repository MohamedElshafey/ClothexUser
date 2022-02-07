package com.clothex.data.remote.repository.notification

import com.clothex.data.domain.model.notification.Notification
import com.clothex.data.domain.repository.notification.IGetNotificationRepository
import com.clothex.data.remote.api.NotificationApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class GetNotificationRepository(private val apiService: NotificationApiService) :
    IGetNotificationRepository {
    override suspend fun getNotifications(): Flow<List<Notification>> =
        flow { emit(apiService.getNotifications()) }
}