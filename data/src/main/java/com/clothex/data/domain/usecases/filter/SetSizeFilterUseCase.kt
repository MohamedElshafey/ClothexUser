package com.clothex.data.domain.usecases.filter

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.ILocalDataSource

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias SetSizeFilterBaseUseCase = BaseUseCase<String, Unit>

class SetSizeFilterUseCase(private val repository: ILocalDataSource) : SetSizeFilterBaseUseCase {
    override suspend fun invoke(params: String) = repository.setSize(params)
}