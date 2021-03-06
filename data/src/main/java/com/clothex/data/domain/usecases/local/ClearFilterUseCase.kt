package com.clothex.data.domain.usecases.local

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.shared_pref.ILocalDataSource

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias ClearFilterBaseUseCase = BaseUseCase<Unit, Unit>

class ClearFilterUseCase(private val repository: ILocalDataSource) : ClearFilterBaseUseCase {
    override suspend fun invoke(params: Unit) = repository.clearFilterPref()
}