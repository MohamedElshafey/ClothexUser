package com.clothex.data.domain.usecases.sign

import com.clothex.data.domain.model.BaseResponseModel
import com.clothex.data.domain.model.sign.Login
import com.clothex.data.domain.model.sign.LoginBody
import com.clothex.data.domain.repository.sign.ILoginRepository
import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.domain.usecases.token.SetTokenUseCase
import com.clothex.data.domain.usecases.token.Token
import com.clothex.data.domain.usecases.user.SetUserUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias LoginBaseUseCase = BaseUseCase<LoginBody, Flow<Result<BaseResponseModel<Login>>>>

class LoginUseCase(
    private val repository: ILoginRepository,
    private val setTokenUseCase: SetTokenUseCase,
    private val setUserUseCase: SetUserUseCase
) :
    LoginBaseUseCase {
    override suspend fun invoke(params: LoginBody): Flow<Result<BaseResponseModel<Login>>> = flow {
        repository.login(params).collect {
            it.data?.let { login ->
                setTokenUseCase.invoke(Token(login.token, false))
                setUserUseCase.invoke(login.user)
            }
            emit(Result.success(it))
        }
    }.catch {
        emit(Result.failure(Throwable("")))
    }
}