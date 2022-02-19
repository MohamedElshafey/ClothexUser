package com.clothex.data.domain.usecases.notification

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.repository.notification.IReadNotificationRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias ReadNotificationsBaseUseCase = BaseUseCase<String, Flow<Result<SimpleResponse>>>

class ReadNotificationsUseCase(private val repository: IReadNotificationRepository) :
    ReadNotificationsBaseUseCase {

    override suspend fun invoke(params: String): Flow<Result<SimpleResponse>> = flow {
        try {
            repository.readNotification(params).collect {
                emit(Result.success(it))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}