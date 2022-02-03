package com.clothex.data.domain.usecases.user

import com.clothex.data.domain.model.user.User
import com.clothex.data.domain.usecases.BaseUseCase
import com.clothex.data.local.shared_pref.ILocalDataSource

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias SetUserBaseUseCase = BaseUseCase<User, Unit>

class SetUserUseCase(private val repository: ILocalDataSource) : SetUserBaseUseCase {
    override suspend fun invoke(params: User) {
        repository.setUser(params)
    }
}