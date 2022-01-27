package com.clothex.data.remote.api

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.sign.Login
import com.clothex.data.domain.model.sign.LoginBody
import com.clothex.data.domain.model.sign.SignupBody
import retrofit2.http.*

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface SigningApiService {
    @POST("login")
    suspend fun login(@Body body: LoginBody): Login

    @POST("signup")
    suspend fun signup(@Body body: SignupBody): SimpleResponse

    @GET("signup-temporary")
    suspend fun signupTemporary(): Login

    @PUT("update-fcm-token")
    suspend fun updateFCMToken(@Query("fcm_token") fcmToken: String): SimpleResponse
}