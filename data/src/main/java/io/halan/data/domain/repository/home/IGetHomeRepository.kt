package io.halan.data.domain.repository.home

import io.halan.data.domain.model.Home
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface IGetHomeRepository {
    suspend fun getHome(): Flow<Home?>
}