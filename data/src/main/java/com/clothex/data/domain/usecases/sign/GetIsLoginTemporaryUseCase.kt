package com.clothex.data.domain.usecases.sign

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.shared_pref.ILocalDataSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias LoginTemporaryBaseUseCase = BaseUseCase<Unit, Flow<Boolean>>

class GetIsLoginTemporaryUseCase(private val repository: ILocalDataSource) :
    LoginTemporaryBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<Boolean> = repository.getIsLoginTemporary()
}