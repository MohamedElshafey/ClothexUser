package com.clothex.data.domain.repository.my_item

import com.clothex.data.domain.model.my_item.MyItem
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface IGetMyItemsRepository {
    suspend fun getMyItems(): Flow<List<MyItem>?>
}