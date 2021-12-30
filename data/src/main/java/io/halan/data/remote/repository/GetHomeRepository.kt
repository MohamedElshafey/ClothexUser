package io.halan.data.remote.repository

import io.halan.data.domain.model.Home
import io.halan.data.domain.repository.home.IGetHomeRepository
import io.halan.data.remote.api.HomeApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class GetHomeRepository(private val apiService: HomeApiService) : IGetHomeRepository {
    override suspend fun getHome(): Flow<Home> = flow { emit(apiService.getHome()) }
}