package com.clothex.data.domain.usecases.sign

import com.clothex.data.domain.model.sign.Login
import com.clothex.data.domain.model.sign.LoginBody
import com.clothex.data.domain.repository.sign.ILoginRepository
import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.domain.usecases.token.SetTokenUseCase
import com.clothex.data.domain.usecases.token.Token
import com.clothex.data.domain.usecases.user.SetUserUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias LoginBaseUseCase = BaseUseCase<LoginBody, Flow<Result<Login>>>

class LoginUseCase(
    private val repository: ILoginRepository,
    private val setTokenUseCase: SetTokenUseCase,
    private val setUserUseCase: SetUserUseCase
) :
    LoginBaseUseCase {
    override suspend fun invoke(params: LoginBody): Flow<Result<Login>> = flow {
        try {
            repository.login(params).collect {
                setTokenUseCase.invoke(Token(it.token, false))
                setUserUseCase.invoke(it.user)
                emit(Result.success(it))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}