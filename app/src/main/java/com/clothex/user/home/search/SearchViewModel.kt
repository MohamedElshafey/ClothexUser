package com.clothex.user.home.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.body.ProductBody
import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.data.domain.usecases.filter.GetColorFilterUseCase
import com.clothex.data.domain.usecases.filter.GetPriceEndFilterUseCase
import com.clothex.data.domain.usecases.filter.GetPriceStartFilterUseCase
import com.clothex.data.domain.usecases.filter.GetSizeFilterUseCase
import com.clothex.data.domain.usecases.product.GetProductPageUseCase
import com.clothex.data.domain.usecases.sort.GetSortUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(
    private val productPageUseCase: GetProductPageUseCase,
    private val getSortUseCase: GetSortUseCase,
    private val getSizeFilterUseCase: GetSizeFilterUseCase,
    private val getColorFilterUseCase: GetColorFilterUseCase,
    private val getPriceStartFilterUseCase: GetPriceStartFilterUseCase,
    private val getPriceEndFilterUseCase: GetPriceEndFilterUseCase
) : ViewModel() {

    val productLiveData = MutableLiveData<List<HomeProduct>>()

    var productPage: Int = 0

    fun reset() {
        productPage = 0
    }

    var shopId: String? = null
    var sort: String? = null
    var size: String? = null
    var color: String? = null
    var priceStart: Int? = null
    var priceEnd: Int? = null

    private suspend fun fetchLocalData() {
        sort = getSortUseCase(Unit).first()
        size = getSizeFilterUseCase(Unit).first()
        color = getColorFilterUseCase(Unit).first()
        priceStart = getPriceStartFilterUseCase(Unit).first()
        priceEnd = getPriceEndFilterUseCase(Unit).first()
    }

    fun fetchProductPage(search: String?) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                fetchLocalData()
                productPageUseCase.invoke(
                    ProductBody(
                        page = productPage,
                        sort = sort,
                        priceStart = priceStart,
                        priceEnd = priceEnd,
                        size = size,
                        shopId = shopId,
                        color = color,
                        search = search
                    )
                ).collect {
                    productPage++
                    productLiveData.postValue(it)
                }
            }
        }
    }
}