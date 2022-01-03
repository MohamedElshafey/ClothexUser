package com.clothex.data.domain.usecases.local

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.ILocalDataSource

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias ClearSessionBaseUseCase = BaseUseCase<Unit, Unit>

class ClearSessionUseCase(private val repository: ILocalDataSource) : ClearSessionBaseUseCase {
    override suspend fun invoke(params: Unit) = repository.clearSessionPref()
}