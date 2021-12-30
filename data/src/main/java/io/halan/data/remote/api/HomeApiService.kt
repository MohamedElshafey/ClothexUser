package io.halan.data.remote.api

import io.halan.data.domain.model.Home
import retrofit2.http.GET

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface HomeApiService {
    @GET("home")
    suspend fun getHome(): Home
}