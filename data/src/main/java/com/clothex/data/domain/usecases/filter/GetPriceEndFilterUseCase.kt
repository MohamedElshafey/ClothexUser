package com.clothex.data.domain.usecases.filter

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.ILocalDataSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetPriceEndFilterBaseUseCase = BaseUseCase<Unit, Flow<Int?>>

class GetPriceEndFilterUseCase(private val repository: ILocalDataSource) :
    GetPriceEndFilterBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<Int?> = repository.getPriceEnd()
}