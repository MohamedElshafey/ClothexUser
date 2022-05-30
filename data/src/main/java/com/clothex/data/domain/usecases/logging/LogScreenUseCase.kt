package com.clothex.data.domain.usecases.logging

import com.clothex.data.domain.repository.logging.ILogScreenRepository
import com.clothex.data.domain.usecases.BaseUseCase

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias LogScreenBaseUseCase = BaseUseCase<String, Unit>

class LogScreenUseCase(private val repository: ILogScreenRepository) : LogScreenBaseUseCase {
    override suspend fun invoke(params: String) = repository.logScreen(params)
}