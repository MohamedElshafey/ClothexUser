package com.clothex.user.onboarding.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.usecases.onboarding.GetVisitOnBoardingUseCase
import com.clothex.data.domain.usecases.sign.SignUpTemporaryUseCase
import com.clothex.data.domain.usecases.token.GetTokenUseCase
import com.clothex.data.domain.usecases.token.SetTokenUseCase
import com.clothex.data.domain.usecases.token.Token
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashViewModel(
    getTokenUseCase: GetTokenUseCase,
    setTokenUseCase: SetTokenUseCase,
    signUpTemporaryUseCase: SignUpTemporaryUseCase,
    getVisitOnBoardingUseCase: GetVisitOnBoardingUseCase
) : ViewModel() {

    val tokenLiveData = MutableLiveData<String>()
    val shouldNavigateToOnBoarding = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            getVisitOnBoardingUseCase.invoke(Unit).collect {
                shouldNavigateToOnBoarding.postValue(it.not())
            }
            getTokenUseCase(Unit).collect { token ->
                if (token.isNullOrEmpty()) {
                    signUpTemporaryUseCase(Unit).collect { login ->
                        login?.let {
                            setTokenUseCase(Token(login.token, true))
                            tokenLiveData.postValue(token)
                        }
                    }
                } else {
                    tokenLiveData.postValue(token)
                }
            }
        }
    }

}