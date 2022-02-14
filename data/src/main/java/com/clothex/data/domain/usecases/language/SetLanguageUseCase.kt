package com.clothex.data.domain.usecases.language

import com.clothex.data.domain.model.Language
import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.shared_pref.ILocalDataSource

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias SetLanguageBaseUseCase = BaseUseCase<Language, Unit>

class SetLanguageUseCase(private val repository: ILocalDataSource) : SetLanguageBaseUseCase {
    override suspend fun invoke(params: Language) {
        repository.setLanguage(params)
    }
}