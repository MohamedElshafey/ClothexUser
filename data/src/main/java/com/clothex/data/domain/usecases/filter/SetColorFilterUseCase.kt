package com.clothex.data.domain.usecases.filter

import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.shared_pref.ILocalDataSource

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias SetColorFilterBaseUseCase = BaseUseCase<String?, Unit>

class SetColorFilterUseCase(private val repository: ILocalDataSource) : SetColorFilterBaseUseCase {
    override suspend fun invoke(params: String?) = repository.setColor(params)
}