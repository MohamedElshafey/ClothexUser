package com.clothex.data.remote.repository

import com.clothex.data.domain.model.home.Home
import com.clothex.data.domain.repository.home.IGetHomeRepository
import com.clothex.data.remote.api.HomeApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class GetHomeRepository(private val apiService: HomeApiService) : IGetHomeRepository {
    override suspend fun getHome(): Flow<Home> = flow { emit(apiService.getHome()) }
}