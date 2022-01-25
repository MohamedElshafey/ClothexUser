package com.clothex.data.domain.usecases.sign

import com.clothex.data.domain.model.sign.Login
import com.clothex.data.domain.repository.sign.ISignUpTemporaryRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias SignUpTemporaryBaseUseCase = BaseUseCase<Unit, Flow<Login?>>

class SignUpTemporaryUseCase(private val repository: ISignUpTemporaryRepository) :
    SignUpTemporaryBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<Login?> = repository.signUpTemporary()
}