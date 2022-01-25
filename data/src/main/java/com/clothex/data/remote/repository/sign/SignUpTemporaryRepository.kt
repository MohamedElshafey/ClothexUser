package com.clothex.data.remote.repository.sign

import com.clothex.data.domain.model.sign.Login
import com.clothex.data.domain.repository.sign.ISignUpTemporaryRepository
import com.clothex.data.remote.api.SigningApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class SignUpTemporaryRepository(private val apiService: SigningApiService) :
    ISignUpTemporaryRepository {
    override suspend fun signUpTemporary(): Flow<Login?> = flow {
        emit(apiService.signupTemporary())
    }
}