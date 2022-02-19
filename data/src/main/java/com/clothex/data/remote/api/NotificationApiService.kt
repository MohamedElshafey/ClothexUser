package com.clothex.data.remote.api

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.notification.Notification
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface NotificationApiService {
    @GET("my-notifications")
    suspend fun getNotifications(): List<Notification>

    @POST("read-notification")
    suspend fun readNotification(@Query("notification_id") notificationId: String): SimpleResponse
}