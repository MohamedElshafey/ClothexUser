package com.clothex.data.domain.usecases.filter

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.shared_pref.ILocalDataSource

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias SetPriceEndFilterBaseUseCase = BaseUseCase<Int?, Unit>

class SetPriceEndFilterUseCase(private val repository: ILocalDataSource) :
    SetPriceEndFilterBaseUseCase {
    override suspend fun invoke(params: Int?) = repository.setPriceEnd(params)
}