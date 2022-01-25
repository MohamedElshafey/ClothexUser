package com.clothex.user.home.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.sign.Login
import com.clothex.data.domain.model.sign.LoginBody
import com.clothex.data.domain.usecases.sign.LoginUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    fun login(loginBody: LoginBody, callback: (Result<Login>) -> Unit) {
        viewModelScope.launch {
            loginUseCase.invoke(loginBody).collect {
                callback.invoke(it)
            }
        }
    }
}