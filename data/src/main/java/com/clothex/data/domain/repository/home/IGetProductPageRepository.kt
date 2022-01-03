package com.clothex.data.domain.repository.home

import com.clothex.data.domain.model.home.HomeProduct
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface IGetProductPageRepository {
    suspend fun getProducts(page: Int): Flow<List<HomeProduct>?>
}