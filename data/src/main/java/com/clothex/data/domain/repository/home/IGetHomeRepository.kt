package com.clothex.data.domain.repository.home

import com.clothex.data.domain.model.home.Home
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface IGetHomeRepository {
    suspend fun getHome(): Flow<Home>
}