package com.clothex.user.di

import com.clothex.data.remote.repository.GetHomeRepository
import com.clothex.data.remote.repository.GetProductDetailsRepository
import com.clothex.data.remote.repository.GetProductPageRepository
import com.clothex.data.remote.repository.GetShopDetailsRepository
import com.clothex.data.remote.repository.my_item.CreateMyItemRepository
import com.clothex.data.remote.repository.my_item.DeleteMyItemRepository
import com.clothex.data.remote.repository.my_item.GetMyItemsRepository
import org.koin.dsl.module

/**
 * Created by Mohamed Elshafey on 10/9/2020.
 */

val remoteDataSourceModule = module {
    single { GetHomeRepository(apiService = get()) }
    single { GetProductDetailsRepository(apiService = get()) }
    single { GetProductPageRepository(apiService = get()) }
    single { GetShopDetailsRepository(apiService = get()) }
    single { GetMyItemsRepository(apiService = get()) }
    single { CreateMyItemRepository(apiService = get()) }
    single { DeleteMyItemRepository(apiService = get()) }
}