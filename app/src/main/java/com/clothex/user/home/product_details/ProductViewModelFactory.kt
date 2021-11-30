package com.clothex.user.home.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clothex.shop.core.models.Item

/**
 * Created by Mohamed Elshafey on 29/11/2021.
 */
class ProductViewModelFactory(val item: Item) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductDetailsViewModel(item) as T
    }
}