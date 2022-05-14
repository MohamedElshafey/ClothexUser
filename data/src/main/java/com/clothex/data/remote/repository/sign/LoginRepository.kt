package com.clothex.data.remote.repository.sign

import com.clothex.data.domain.model.BaseResponseModel
import com.clothex.data.domain.model.sign.Login
import com.clothex.data.domain.model.sign.LoginBody
import com.clothex.data.domain.repository.sign.ILoginRepository
import com.clothex.data.remote.api.SigningApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class LoginRepository(private val apiService: SigningApiService) : ILoginRepository {
    override suspend fun login(loginBody: LoginBody): Flow<BaseResponseModel<Login>> = flow {
        emit(apiService.login(loginBody))
    }
}