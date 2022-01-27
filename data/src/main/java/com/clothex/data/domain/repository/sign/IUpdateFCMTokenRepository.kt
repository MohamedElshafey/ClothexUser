package com.clothex.data.domain.repository.sign

import com.clothex.data.domain.model.SimpleResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface IUpdateFCMTokenRepository {
    suspend fun updateFCMToken(fcmToken: String): Flow<SimpleResponse>
}