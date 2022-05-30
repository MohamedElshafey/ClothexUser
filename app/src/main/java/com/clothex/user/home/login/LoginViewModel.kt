package com.clothex.user.home.login

import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.BaseResponseModel
import com.clothex.data.domain.model.sign.Login
import com.clothex.data.domain.model.sign.LoginBody
import com.clothex.data.domain.usecases.local.SetIsFirstTimeOpenUseCase
import com.clothex.data.domain.usecases.sign.LoginUseCase
import com.clothex.data.domain.usecases.sign.SignUpTemporaryUseCase
import com.clothex.data.domain.usecases.token.GetTokenUseCase
import com.clothex.data.domain.usecases.token.SetTokenUseCase
import com.clothex.data.domain.usecases.token.Token
import com.clothex.user.base.BaseLanguageViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val setIsFirstTimeOpenUseCase: SetIsFirstTimeOpenUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val setTokenUseCase: SetTokenUseCase,
    private val signUpTemporaryUseCase: SignUpTemporaryUseCase,
) : BaseLanguageViewModel() {

    fun getTokenIfNeeded() {
        viewModelScope.launch {
            getTokenUseCase(Unit).collect { token ->
                if (token.isNullOrEmpty()) {
                    signUpTemporaryUseCase(Unit).collect { result ->
                        result.getOrNull()?.let { login ->
                            setTokenUseCase(Token(login.token, true))
                        }
                    }
                }
            }
        }
    }

    fun setFirstTimeOpen() {
        viewModelScope.launch {
            setIsFirstTimeOpenUseCase.invoke(false)
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