package com.clothex.data.domain.usecases.sign

import com.clothex.data.domain.model.sign.Login
import com.clothex.data.domain.repository.sign.ISignUpTemporaryRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias SignUpTemporaryBaseUseCase = BaseUseCase<Unit, Flow<Result<Login?>>>

class SignUpTemporaryUseCase(private val repository: ISignUpTemporaryRepository) :
    SignUpTemporaryBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<Result<Login?>> = flow {
        try {
            repository.signUpTemporary().collect {
                emit(Result.success(it))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}