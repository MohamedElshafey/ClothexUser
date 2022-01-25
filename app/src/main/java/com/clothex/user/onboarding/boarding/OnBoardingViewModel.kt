package com.clothex.user.onboarding.boarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.usecases.onboarding.SetVisitOnBoardingUseCase
import kotlinx.coroutines.launch

class OnBoardingViewModel(private val setVisitOnBoardingUseCase: SetVisitOnBoardingUseCase) :
    ViewModel() {

    fun changeVisitOnBoardingState() {
        viewModelScope.launch {
            setVisitOnBoardingUseCase.invoke(true)
        }
    }
}