package com.clothex.data.domain.usecases.token

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.shared_pref.ILocalDataSource

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias SetTokenBaseUseCase = BaseUseCase<Token, Unit>

data class Token(val token: String, val isTemporary: Boolean)

class SetTokenUseCase(private val repository: ILocalDataSource) : SetTokenBaseUseCase {
    override suspend fun invoke(params: Token) {
        repository.setToken(params.token)
        repository.setIsLoginTemporary(params.isTemporary)
    }
}