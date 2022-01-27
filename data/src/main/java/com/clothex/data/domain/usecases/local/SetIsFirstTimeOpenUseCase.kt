package com.clothex.data.domain.usecases.local

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.ILocalDataSource

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias SetIsFirstTimeOpenBaseUseCase = BaseUseCase<Boolean, Unit>

class SetIsFirstTimeOpenUseCase(private val repository: ILocalDataSource) :
    SetIsFirstTimeOpenBaseUseCase {
    override suspend fun invoke(params: Boolean) = repository.setIsFirstTime(params)
}