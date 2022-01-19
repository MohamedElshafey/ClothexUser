package com.clothex.data.domain.repository.my_item

import com.clothex.data.domain.model.body.MyItemBody
import com.clothex.data.domain.model.my_item.MyItem
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface ICreateMyItemRepository {
    suspend fun createMyItem(myItemBody: MyItemBody): Flow<MyItem?>
}