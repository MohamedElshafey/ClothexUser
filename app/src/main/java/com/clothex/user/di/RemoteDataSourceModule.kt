package com.clothex.user.di

import com.clothex.data.remote.repository.GetHomeRepository
import com.clothex.data.remote.repository.GetProductDetailsRepository
import com.clothex.data.remote.repository.GetProductPageRepository
import org.koin.dsl.module

/**
 * Created by Mohamed Elshafey on 10/9/2020.
 */

val remoteDataSourceModule = module {
    single { GetHomeRepository(apiService = get()) }
    single { GetProductDetailsRepository(apiService = get()) }
    single { GetProductPageRepository(apiService = get()) }
}