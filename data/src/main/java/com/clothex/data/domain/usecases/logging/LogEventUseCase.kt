package com.clothex.data.domain.usecases.logging

import com.clothex.data.domain.model.log.EventBase
import com.clothex.data.domain.repository.logging.ILogEventRepository
import com.clothex.data.domain.usecases.BaseUseCase

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */


typealias LogEventBaseUseCase = BaseUseCase<EventBase, Unit>

class LogEventUseCase(private val repository: ILogEventRepository) : LogEventBaseUseCase {
    override suspend fun invoke(params: EventBase) =
        repository.logEvent(params.eventName, params.getParams())
}