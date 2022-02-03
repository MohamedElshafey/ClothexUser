package com.clothex.data.domain.usecases.user

import com.clothex.data.domain.model.user.User
import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.shared_pref.ILocalDataSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetUserBaseUseCase = BaseUseCase<Unit, Flow<User?>>

class GetUserUseCase(private val repository: ILocalDataSource) : GetUserBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<User?> = repository.getUser()
}