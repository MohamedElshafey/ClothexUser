package com.clothex.data.domain.usecases.language

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.shared_pref.ILocalDataSource

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetLanguageBaseUseCase = BaseUseCase<Unit, String>

class GetLanguageUseCase(private val repository: ILocalDataSource) : GetLanguageBaseUseCase {
    override suspend fun invoke(params: Unit): String = repository.getLanguage()
}