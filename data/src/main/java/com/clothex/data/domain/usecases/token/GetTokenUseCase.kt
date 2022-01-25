package com.clothex.data.domain.usecases.token

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.ILocalDataSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetTokenBaseUseCase = BaseUseCase<Unit, Flow<String?>>

class GetTokenUseCase(private val repository: ILocalDataSource) : GetTokenBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<String?> = repository.getToken()
}