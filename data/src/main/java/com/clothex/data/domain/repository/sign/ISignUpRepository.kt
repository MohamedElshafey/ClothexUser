package com.clothex.data.domain.repository.sign

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.sign.SignupBody
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface ISignUpRepository {
    suspend fun signUp(signupBody: SignupBody): Flow<SimpleResponse>
}