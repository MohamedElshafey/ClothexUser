package com.clothex.user.profile.edit

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.usecases.user.GetUserUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Mohamed Elshafey on 10/12/2021.
 */
class EditProfileViewModel(private val getUserUseCase: GetUserUseCase) : ViewModel() {
    val userName = ObservableField<String>()
    val phoneNumber = ObservableField<String>()
    val email = ObservableField<String>()

    fun fetchUser() {
        viewModelScope.launch {
            getUserUseCase(Unit).collect {
                userName.set(it?.username)
                email.set(it?.email)
                phoneNumber.set(it?.phoneNumber)
            }
        }
    }

}