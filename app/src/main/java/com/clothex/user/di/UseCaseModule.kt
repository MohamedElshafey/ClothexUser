package com.clothex.user.di

import com.clothex.data.domain.usecases.filter.*
import com.clothex.data.domain.usecases.home.GetHomeUseCase
import com.clothex.data.domain.usecases.local.ClearSessionUseCase
import com.clothex.data.domain.usecases.my_item.CreateMyItemsUseCase
import com.clothex.data.domain.usecases.my_item.DeleteMyItemsUseCase
import com.clothex.data.domain.usecases.my_item.GetMyItemsUseCase
import com.clothex.data.domain.usecases.order.CreateMyOrdersUseCase
import com.clothex.data.domain.usecases.order.GetMyOrdersUseCase
import com.clothex.data.domain.usecases.product.GetProductDetailsUseCase
import com.clothex.data.domain.usecases.product.GetProductPageUseCase
import com.clothex.data.domain.usecases.shop.GetShopDetailsUseCase
import com.clothex.data.domain.usecases.shop.GetShopPageUseCase
import com.clothex.data.domain.usecases.sort.GetSortUseCase
import com.clothex.data.domain.usecases.sort.SetSortUseCase
import com.clothex.data.local.LocalDataSourceImpl
import com.clothex.data.remote.repository.GetHomeRepository
import com.clothex.data.remote.repository.GetProductDetailsRepository
import com.clothex.data.remote.repository.GetShopDetailsRepository
import com.clothex.data.remote.repository.my_item.CreateMyItemRepository
import com.clothex.data.remote.repository.my_item.DeleteMyItemRepository
import com.clothex.data.remote.repository.my_item.GetMyItemsRepository
import com.clothex.data.remote.repository.order.CreateMyOrderRepository
import com.clothex.data.remote.repository.order.GetMyOrdersRepository
import com.clothex.data.remote.repository.search.GetProductPageRepository
import com.clothex.data.remote.repository.search.GetShopPageRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by Mohamed Elshafey on 10/9/2020.
 */

val useCaseModule = module {
    single(named("get_home")) { provideGetHomeUseCase(get()) }
    single(named("get_product_page")) { provideGetProductPageUseCase(get()) }
    single(named("get_product_details")) { provideGetProductDetailsUseCase(get()) }
    single(named("clear_session")) { provideClearSessionUseCase(get()) }

    single(named("get_shop_details")) { provideGetShopDetailsUseCase(get()) }

    single(named("get_sort")) { provideGetSortUseCase(get()) }
    single(named("set_sort")) { provideSetSortUseCase(get()) }

    single(named("get_size_filter")) { provideGetSizeFilterUseCase(get()) }
    single(named("set_size_filter")) { provideSetSizeFilterUseCase(get()) }

    single(named("get_color_filter")) { provideGetColorFilterUseCase(get()) }
    single(named("set_color_filter")) { provideSetColorFilterUseCase(get()) }

    single(named("get_price_start_filter")) { provideGetPriceStartFilterUseCase(get()) }
    single(named("set_price_start_filter")) { provideSetPriceStartFilterUseCase(get()) }

    single(named("get_price_end_filter")) { provideGetPriceEndFilterUseCase(get()) }
    single(named("set_price_end_filter")) { provideSetPriceEndFilterUseCase(get()) }

    single(named("get_my_items")) { provideGetMyItemsUseCase(get()) }
    single(named("create_my_item")) { provideCreateMyItemUseCase(get()) }
    single(named("delete_my_item")) { provideDeleteMyItemUseCase(get()) }

    single(named("get_my_orders")) { provideGetMyOrdersUseCase(get()) }
    single(named("create_my_order")) { provideCreateMyOrderUseCase(get()) }

    single(named("get_shop_page")) { provideGetShopPageUseCase(get()) }
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

fun provideGetSortUseCase(dataSource: LocalDataSourceImpl): GetSortUseCase {
    return GetSortUseCase(dataSource)
}

fun provideSetSortUseCase(dataSource: LocalDataSourceImpl): SetSortUseCase {
    return SetSortUseCase(dataSource)
}

fun provideClearSessionUseCase(dataSource: LocalDataSourceImpl): ClearSessionUseCase {
    return ClearSessionUseCase(dataSource)
}

fun provideGetSizeFilterUseCase(dataSource: LocalDataSourceImpl): GetSizeFilterUseCase {
    return GetSizeFilterUseCase(dataSource)
}

fun provideSetSizeFilterUseCase(dataSource: LocalDataSourceImpl): SetSizeFilterUseCase {
    return SetSizeFilterUseCase(dataSource)
}

fun provideGetColorFilterUseCase(dataSource: LocalDataSourceImpl): GetColorFilterUseCase {
    return GetColorFilterUseCase(dataSource)
}

fun provideSetColorFilterUseCase(dataSource: LocalDataSourceImpl): SetColorFilterUseCase {
    return SetColorFilterUseCase(dataSource)
}

fun provideGetPriceStartFilterUseCase(dataSource: LocalDataSourceImpl): GetPriceStartFilterUseCase {
    return GetPriceStartFilterUseCase(dataSource)
}

fun provideSetPriceStartFilterUseCase(dataSource: LocalDataSourceImpl): SetPriceStartFilterUseCase {
    return SetPriceStartFilterUseCase(dataSource)
}

fun provideGetPriceEndFilterUseCase(dataSource: LocalDataSourceImpl): GetPriceEndFilterUseCase {
    return GetPriceEndFilterUseCase(dataSource)
}

fun provideSetPriceEndFilterUseCase(dataSource: LocalDataSourceImpl): SetPriceEndFilterUseCase {
    return SetPriceEndFilterUseCase(dataSource)
}

fun provideGetShopDetailsUseCase(repository: GetShopDetailsRepository): GetShopDetailsUseCase {
    return GetShopDetailsUseCase(repository)
}

fun provideGetMyItemsUseCase(repository: GetMyItemsRepository): GetMyItemsUseCase {
    return GetMyItemsUseCase(repository)
}

fun provideCreateMyItemUseCase(repository: CreateMyItemRepository): CreateMyItemsUseCase {
    return CreateMyItemsUseCase(repository)
}

fun provideDeleteMyItemUseCase(repository: DeleteMyItemRepository): DeleteMyItemsUseCase {
    return DeleteMyItemsUseCase(repository)
}

fun provideGetMyOrdersUseCase(repository: GetMyOrdersRepository): GetMyOrdersUseCase {
    return GetMyOrdersUseCase(repository)
}

fun provideCreateMyOrderUseCase(repository: CreateMyOrderRepository): CreateMyOrdersUseCase {
    return CreateMyOrdersUseCase(repository)
}

fun provideGetShopPageUseCase(repository: GetShopPageRepository): GetShopPageUseCase {
    return GetShopPageUseCase(repository)
}

