package com.clothex.data.domain.usecases.filter

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.ILocalDataSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetColorFilterBaseUseCase = BaseUseCase<Unit, Flow<String?>>

class GetColorFilterUseCase(private val repository: ILocalDataSource) : GetColorFilterBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<String?> = repository.getColor()
}