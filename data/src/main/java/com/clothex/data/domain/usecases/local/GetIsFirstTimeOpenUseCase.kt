package com.clothex.data.domain.usecases.local

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.ILocalDataSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetIsFirstTimeOpenBaseUseCase = BaseUseCase<Unit, Flow<Boolean>>

class GetIsFirstTimeOpenUseCase(private val repository: ILocalDataSource) :
    GetIsFirstTimeOpenBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<Boolean> = repository.getIsFirstTime()
}