package com.clothex.user.home.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.body.ProductBody
import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.data.domain.usecases.product.GetProductPageUseCase
import com.clothex.data.domain.usecases.sort.GetSortUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(
    private val productPageUseCase: GetProductPageUseCase,
    private val getSortUseCase: GetSortUseCase
) : ViewModel() {

    val productLiveData = MutableLiveData<List<HomeProduct>>()

    var productPage: Int = 0

    fun reset() {
        productPage = 0
    }

    fun fetchProductPage() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getSortUseCase.invoke(Unit).collect { sort ->
                    productPageUseCase.invoke(ProductBody(productPage, sort)).collect {
                        productPage++
                        productLiveData.postValue(it)
                    }
                }

            }
        }
    }
}