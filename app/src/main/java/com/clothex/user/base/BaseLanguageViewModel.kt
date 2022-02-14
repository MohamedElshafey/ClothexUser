package com.clothex.user.base

import androidx.lifecycle.ViewModel
import com.clothex.data.domain.model.Language
import com.clothex.data.local.shared_pref.LocalDataSourceImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseLanguageViewModel : ViewModel(), KoinComponent {

    private val localDataStoreImpl: LocalDataSourceImpl by inject()

    fun isArabic(): Boolean = getLanguage() == Language.ARABIC.value

    fun getLanguage(): String = localDataStoreImpl.getLanguage()

}