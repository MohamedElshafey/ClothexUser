package com.clothex.data.remote.repository.sign

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.sign.SignupBody
import com.clothex.data.domain.repository.sign.ISignUpRepository
import com.clothex.data.remote.api.SigningApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class SignUpRepository(private val apiService: SigningApiService) : ISignUpRepository {
    override suspend fun signUp(signupBody: SignupBody): Flow<SimpleResponse> = flow {
        emit(apiService.signup(signupBody))
    }
}