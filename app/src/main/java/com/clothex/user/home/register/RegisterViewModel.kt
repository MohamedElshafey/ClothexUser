package com.clothex.user.home.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.BaseResponseModel
import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.sign.Login
import com.clothex.data.domain.model.sign.LoginBody
import com.clothex.data.domain.model.sign.SignupBody
import com.clothex.data.domain.usecases.sign.LoginUseCase
import com.clothex.data.domain.usecases.sign.SignUpTemporaryUseCase
import com.clothex.data.domain.usecases.sign.SignUpUseCase
import com.clothex.data.domain.usecases.token.GetTokenUseCase
import com.clothex.data.domain.usecases.token.SetTokenUseCase
import com.clothex.data.domain.usecases.token.Token
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val loginUseCase: LoginUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val setTokenUseCase: SetTokenUseCase,
    private val signUpTemporaryUseCase: SignUpTemporaryUseCase,
) : ViewModel() {

    fun getTokenIfNeeded() {
        viewModelScope.launch {
            getTokenUseCase(Unit).collect { token ->
                if (token.isNullOrEmpty()) {
                    signUpTemporaryUseCase(Unit).collect { login ->
                        login?.let {
                            setTokenUseCase(Token(login.token, true))
                        }
                    }
                }
            }
        }
    }

    fun signup(signupBody: SignupBody, callback: (Result<SimpleResponse>) -> Unit) {
        viewModelScope.launch {
            signUpUseCase(signupBody).collect { simpleResponseResult ->
                callback.invoke(simpleResponseResult)
            }
        }
    }

    fun login(loginBody: LoginBody, callback: (Result<BaseResponseModel<Login>>) -> Unit) {
        viewModelScope.launch {
            loginUseCase.invoke(loginBody).collect {
                callback.invoke(it)
            }
        }
    }
}