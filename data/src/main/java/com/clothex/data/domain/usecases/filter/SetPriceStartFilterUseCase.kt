package com.clothex.data.domain.usecases.filter

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.ILocalDataSource

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias SetPriceStartFilterBaseUseCase = BaseUseCase<Int, Unit>

class SetPriceStartFilterUseCase(private val repository: ILocalDataSource) :
    SetPriceStartFilterBaseUseCase {
    override suspend fun invoke(params: Int) = repository.setPriceStart(params)
}