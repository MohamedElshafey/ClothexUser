package com.clothex.user.home.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.body.ProductBody
import com.clothex.data.domain.model.body.SortEnum
import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.data.domain.usecases.product.GetProductPageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(private val productPageUseCase: GetProductPageUseCase) : ViewModel() {

    val productLiveData = MutableLiveData<List<HomeProduct>>()

    var productPage: Int = 0

    fun reset() {
        productPage = 0
    }

    fun fetchProductPage(sortEnum: SortEnum = SortEnum.BEST_MATCH) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                productPageUseCase.invoke(ProductBody(productPage, sortEnum)).collect {
                    productPage++
                    productLiveData.postValue(it)
                }
            }
        }
    }
}