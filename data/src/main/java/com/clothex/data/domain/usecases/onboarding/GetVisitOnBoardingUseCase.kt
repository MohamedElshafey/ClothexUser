package com.clothex.data.domain.usecases.onboarding

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.ILocalDataSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetVisitOnBoardingBaseUseCase = BaseUseCase<Unit, Flow<Boolean>>

class GetVisitOnBoardingUseCase(private val repository: ILocalDataSource) :
    GetVisitOnBoardingBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<Boolean> = repository.getVisitOnBoarding()
}