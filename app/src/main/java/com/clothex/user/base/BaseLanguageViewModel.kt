package com.clothex.user.base

import androidx.lifecycle.ViewModel
import com.clothex.data.local.shared_pref.LocalDataSourceImpl

class BaseLanguageViewModel(
    private val localDataSToreImpl: LocalDataSourceImpl
) : ViewModel() {

    fun getLanguage(): String {
        return localDataSToreImpl.getLanguage()
    }

}