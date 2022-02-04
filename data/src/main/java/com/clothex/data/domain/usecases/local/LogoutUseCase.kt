package com.clothex.data.domain.usecases.local

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.shared_pref.ILocalDataSource

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias LogoutBaseUseCase = BaseUseCase<Unit, Unit>

class LogoutUseCase(private val repository: ILocalDataSource) : LogoutBaseUseCase {
    override suspend fun invoke(params: Unit) = repository.logout()
}