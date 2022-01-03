package com.clothex.data.domain.usecases.filter

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.ILocalDataSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetPriceStartFilterBaseUseCase = BaseUseCase<Unit, Flow<Int?>>

class GetPriceStartFilterUseCase(private val repository: ILocalDataSource) :
    GetPriceStartFilterBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<Int?> = repository.getPriceStart()
}