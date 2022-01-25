package com.clothex.data.domain.usecases.home

import com.clothex.data.domain.model.home.Home
import com.clothex.data.domain.repository.home.IGetHomeRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetHomeBaseUseCase = BaseUseCase<Unit, Flow<Result<Home>>>

class GetHomeUseCase(private val repository: IGetHomeRepository) : GetHomeBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<Result<Home>> = flow {
        try {
            repository.getHome().collect {
                emit(Result.success(it))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}