package com.clothex.data.domain.usecases.sign

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.sign.SignupBody
import com.clothex.data.domain.repository.sign.ISignUpRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias SignUpBaseUseCase = BaseUseCase<SignupBody, Flow<Result<SimpleResponse>>>

class SignUpUseCase(private val repository: ISignUpRepository) :
    SignUpBaseUseCase {
    override suspend fun invoke(params: SignupBody): Flow<Result<SimpleResponse>> = flow {
        try {
            repository.signUp(params).collect {
                emit(Result.success(it))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}