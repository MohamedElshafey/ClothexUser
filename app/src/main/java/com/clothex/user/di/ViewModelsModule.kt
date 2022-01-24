package com.clothex.user.di

import com.clothex.user.home.book.BookViewModel
import com.clothex.user.home.home.HomeViewModel
import com.clothex.user.home.product_details.ProductDetailsViewModel
import com.clothex.user.home.search.SearchViewModel
import com.clothex.user.home.search.filter.FilterProductViewModel
import com.clothex.user.home.search.sort.SortProductViewModel
import com.clothex.user.home.shop.details.ShopDetailsViewModel
import com.clothex.user.my_items.items.MyItemsViewModel
import com.clothex.user.my_items.orders.ActiveOrdersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by Mohamed Elshafey on 10/9/2020.
 */

val viewModelsModule = module {

    viewModel {
        HomeViewModel(get(named("get_home")))
    }

    viewModel {
        ProductDetailsViewModel(
            getProductDetailsUseCase = get(named("get_product_details")),
            createMyItemsUseCase = get(named("create_my_item"))
        )
    }

    viewModel {
        SearchViewModel(
            productPageUseCase = get(named("get_product_page")),
            shopPageUseCase = get(named("get_shop_page")),
            getSortUseCase = get(named("get_sort")),
            getSizeFilterUseCase = get(named("get_size_filter")),
            getColorFilterUseCase = get(named("get_color_filter")),
            getPriceStartFilterUseCase = get(named("get_price_start_filter")),
            getPriceEndFilterUseCase = get(named("get_price_end_filter")),
        )
    }

    viewModel {
        SortProductViewModel(get(named("set_sort")), get(named("get_sort")))
    }

    viewModel {
        FilterProductViewModel(
            setSizeFilterUseCase = get(named("set_size_filter")),
            getSizeFilterUseCase = get(named("get_size_filter")),
            setColorFilterUseCase = get(named("set_color_filter")),
            getColorFilterUseCase = get(named("get_color_filter")),
            setPriceStartFilterUseCase = get(named("set_price_start_filter")),
            getPriceStartFilterUseCase = get(named("get_price_start_filter")),
            setPriceEndFilterUseCase = get(named("set_price_end_filter")),
            getPriceEndFilterUseCase = get(named("get_price_end_filter"))
        )
    }

    viewModel {
        ShopDetailsViewModel(get(named("get_shop_details")))
    }


    viewModel {
        MyItemsViewModel(
            getMyItemsUseCase = get(named("get_my_items"))
        )
    }

    viewModel {
        BookViewModel(
            deleteMyItemsUseCase = get(named("delete_my_item")),
            createMyOrdersUseCase = get(named("create_my_order"))
        )
    }

    viewModel {
        ActiveOrdersViewModel(
            getMyOrdersUseCase = get(named("get_my_orders"))
        )
    }
}