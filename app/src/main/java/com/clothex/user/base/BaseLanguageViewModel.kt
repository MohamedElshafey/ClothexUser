package com.clothex.user.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.Language
import com.clothex.data.domain.usecases.logging.LogScreenUseCase
import com.clothex.data.local.shared_pref.LocalDataSourceImpl
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

abstract class BaseLanguageViewModel : ViewModel(), KoinComponent {

    private val localDataStoreImpl: LocalDataSourceImpl by inject()
    private val logScreenUseCase: LogScreenUseCase by inject(named("log_screen"))

    fun isArabic(): Boolean = getLanguage() == Language.ARABIC.value

    fun getLanguage(): String = localDataStoreImpl.getLanguage()

    fun logScreen(screen: String) {
        viewModelScope.launch {
            logScreenUseCase.invoke(screen)
        }
    }

}