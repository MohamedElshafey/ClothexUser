package com.clothex.user.profile

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.usecases.local.LogoutUseCase
import com.clothex.data.domain.usecases.user.GetUserUseCase
import com.clothex.user.base.BaseLanguageViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : BaseLanguageViewModel() {

    val initialUserName = ObservableField<String>()
    val userName = ObservableField<String>()
    val userEmail = ObservableField<String>()
    val isLoggedInUser = ObservableField(false)

    fun fetchUser() {
        viewModelScope.launch {
            getUserUseCase(Unit).collect {
                isLoggedInUser.set(it != null)
                initialUserName.set(it?.username?.first()?.toString() ?: "?")
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