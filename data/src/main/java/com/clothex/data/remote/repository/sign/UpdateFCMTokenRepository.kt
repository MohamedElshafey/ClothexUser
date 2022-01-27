package com.clothex.data.remote.repository.sign

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.repository.sign.IUpdateFCMTokenRepository
import com.clothex.data.remote.api.SigningApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class UpdateFCMTokenRepository(private val apiService: SigningApiService) :
    IUpdateFCMTokenRepository {
    override suspend fun updateFCMToken(fcmToken: String): Flow<SimpleResponse> = flow {
        emit(apiService.updateFCMToken(fcmToken))
    }
}