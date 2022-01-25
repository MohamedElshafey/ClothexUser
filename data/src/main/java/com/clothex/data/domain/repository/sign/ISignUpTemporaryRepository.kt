package com.clothex.data.domain.repository.sign

import com.clothex.data.domain.model.sign.Login
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface ISignUpTemporaryRepository {
    suspend fun signUpTemporary(): Flow<Login?>
}