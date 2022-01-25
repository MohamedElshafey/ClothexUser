package com.clothex.data.domain.usecases.onboarding

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.ILocalDataSource

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias SetVisitOnBoardingBaseUseCase = BaseUseCase<Boolean, Unit>

class SetVisitOnBoardingUseCase(private val repository: ILocalDataSource) :
    SetVisitOnBoardingBaseUseCase {
    override suspend fun invoke(params: Boolean) = repository.setVisitOnBoarding(params)
}