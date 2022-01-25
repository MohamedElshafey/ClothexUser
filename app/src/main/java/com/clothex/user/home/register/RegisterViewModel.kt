package com.clothex.user.home.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.sign.SignupBody
import com.clothex.data.domain.usecases.sign.SignUpUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegisterViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    fun signup(signupBody: SignupBody, callback: (Result<SimpleResponse>) -> Unit) {
        viewModelScope.launch {
            signUpUseCase(signupBody).collect { simpleResponseResult ->
                callback.invoke(simpleResponseResult)
            }
        }
    }

}