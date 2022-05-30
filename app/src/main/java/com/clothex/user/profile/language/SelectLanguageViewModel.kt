package com.clothex.user.profile.language

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.Language
import com.clothex.data.domain.usecases.language.GetLanguageUseCase
import com.clothex.data.domain.usecases.language.SetLanguageUseCase
import com.clothex.user.base.BaseLanguageViewModel
import kotlinx.coroutines.launch

/**
 * Created by Mohamed Elshafey on 10/12/2021.
 */
class SelectLanguageViewModel(
    private val getLanguageUseCase: GetLanguageUseCase,
    private val setLanguageUseCase: SetLanguageUseCase
) : BaseLanguageViewModel() {

    val languageLiveData = MutableLiveData<String>()

    private fun getCurrentLanguage() {
        viewModelScope.launch {
            languageLiveData.postValue(getLanguageUseCase.invoke(Unit))
        }
    }

    fun setLanguage(language: Language) {
        viewModelScope.launch {
            setLanguageUseCase.invoke(language)
        }
    }

    init {
        getCurrentLanguage()
    }

}