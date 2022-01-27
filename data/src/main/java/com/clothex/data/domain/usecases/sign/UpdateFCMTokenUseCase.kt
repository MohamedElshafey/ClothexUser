package com.clothex.data.domain.usecases.sign

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.repository.sign.IUpdateFCMTokenRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias UpdateFCMTokenBaseUseCase = BaseUseCase<String, Flow<Result<SimpleResponse>>>

class UpdateFCMTokenUseCase(private val repository: IUpdateFCMTokenRepository) :
    UpdateFCMTokenBaseUseCase {
    override suspend fun invoke(params: String): Flow<Result<SimpleResponse>> = flow {
        try {
            repository.updateFCMToken(params).collect {
                emit(Result.success(it))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}