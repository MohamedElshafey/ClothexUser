package com.clothex.user.di

import com.clothex.data.domain.usecases.home.GetHomeUseCase
import com.clothex.data.domain.usecases.product.GetProductDetailsUseCase
import com.clothex.data.domain.usecases.product.GetProductPageUseCase
import com.clothex.data.remote.repository.GetHomeRepository
import com.clothex.data.remote.repository.GetProductDetailsRepository
import com.clothex.data.remote.repository.GetProductPageRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by Mohamed Elshafey on 10/9/2020.
 */

val useCaseModule = module {
    single(named("get_home")) { provideGetHomeUseCase(get()) }
    single(named("get_product_page")) { provideGetProductPageUseCase(get()) }
    single(named("get_product_details")) { provideGetProductDetailsUseCase(get()) }
}

fun provideGetHomeUseCase(repository: GetHomeRepository): GetHomeUseCase {
    return GetHomeUseCase(repository)
}

fun provideGetProductPageUseCase(repository: GetProductPageRepository): GetProductPageUseCase {
    return GetProductPageUseCase(repository)
}

fun provideGetProductDetailsUseCase(repository: GetProductDetailsRepository): GetProductDetailsUseCase {
    return GetProductDetailsUseCase(repository)
}


