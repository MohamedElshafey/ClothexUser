package com.clothex.data.domain.usecases.filter

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.shared_pref.ILocalDataSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetSizeFilterBaseUseCase = BaseUseCase<Unit, Flow<String?>>

class GetSizeFilterUseCase(private val repository: ILocalDataSource) : GetSizeFilterBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<String?> = repository.getSize()
}