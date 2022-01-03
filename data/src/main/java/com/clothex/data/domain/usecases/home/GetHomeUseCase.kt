package com.clothex.data.domain.usecases.home

import com.clothex.data.domain.model.home.Home
import com.clothex.data.domain.repository.home.IGetHomeRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetHomeBaseUseCase = BaseUseCase<Unit, Flow<Home?>>

class GetHomeUseCase(private val repository: IGetHomeRepository) : GetHomeBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<Home?> = repository.getHome()
}