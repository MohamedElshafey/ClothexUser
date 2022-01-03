package com.clothex.data.remote.api

import com.clothex.data.domain.model.home.Home
import retrofit2.http.GET

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface HomeApiService {
    @GET("home")
    suspend fun getHome(): Home
}