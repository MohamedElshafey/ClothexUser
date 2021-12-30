package io.halan.data.domain.usecases.home

import io.halan.data.domain.model.Home
import io.halan.data.domain.repository.home.IGetHomeRepository
import io.halan.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetBranchesBaseUseCase = BaseUseCase<Unit, Flow<Home?>>

class GetBranchesUseCase(private val repository: IGetHomeRepository) : GetBranchesBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<Home?> = repository.getHome()
}