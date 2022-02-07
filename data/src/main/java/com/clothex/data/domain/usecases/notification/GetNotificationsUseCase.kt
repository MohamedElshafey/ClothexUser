package com.clothex.data.domain.usecases.notification

import com.clothex.data.domain.model.notification.Notification
import com.clothex.data.domain.repository.notification.IGetNotificationRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetNotificationsBaseUseCase = BaseUseCase<Unit, Flow<Result<List<Notification>>>>

class GetNotificationsUseCase(private val repository: IGetNotificationRepository) :
    GetNotificationsBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<Result<List<Notification>>> = flow {
        try {
            repository.getNotifications().collect {
                emit(Result.success(it))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}