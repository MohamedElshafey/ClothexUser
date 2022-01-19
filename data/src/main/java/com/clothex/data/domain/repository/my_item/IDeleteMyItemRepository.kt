package com.clothex.data.domain.repository.my_item

import com.clothex.data.domain.model.SimpleResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface IDeleteMyItemRepository {
    suspend fun deleteMyItem(id: String): Flow<SimpleResponse?>
}