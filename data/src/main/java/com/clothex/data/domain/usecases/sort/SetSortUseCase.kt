package com.clothex.data.domain.usecases.sort

import com.clothex.data.domain.model.body.SortEnum
import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.ILocalDataStore

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias SetSortBaseUseCase = BaseUseCase<SortEnum, Unit>

class SetSortUseCase(private val repository: ILocalDataStore) : SetSortBaseUseCase {
    override suspend fun invoke(params: SortEnum) = repository.setSortEnum(params)
}