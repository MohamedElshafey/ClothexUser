package com.clothex.user.di

import com.clothex.user.home.home.HomeViewModel
import com.clothex.user.home.product_details.ProductDetailsViewModel
import com.clothex.user.home.search.SearchViewModel
import com.clothex.user.home.search.sort.SortProductViewModel
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
        ProductDetailsViewModel(get(named("get_product_details")))
    }

    viewModel {
        SearchViewModel(get(named("get_product_page")), get(named("get_sort")))
    }

    viewModel {
        SortProductViewModel(get(named("set_sort")), get(named("get_sort")))
    }

}