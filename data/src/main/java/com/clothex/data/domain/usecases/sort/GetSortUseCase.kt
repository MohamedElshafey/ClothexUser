package com.clothex.data.domain.usecases.sort

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.shared_pref.ILocalDataSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetSortBaseUseCase = BaseUseCase<Unit, Flow<String?>>

class GetSortUseCase(private val repository: ILocalDataSource) : GetSortBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<String?> = repository.getSortEnum()
}