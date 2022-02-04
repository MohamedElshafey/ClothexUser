package com.clothex.user.profile

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.usecases.local.LogoutUseCase
import com.clothex.data.domain.usecases.user.GetUserUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    val userName = ObservableField<String>()
    val userEmail = ObservableField<String>()

    fun fetchUser() {
        viewModelScope.launch {
            getUserUseCase(Unit).collect {
                userName.set(it?.username)
                userEmail.set(it?.email)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase.invoke(Unit)
        }
    }

}