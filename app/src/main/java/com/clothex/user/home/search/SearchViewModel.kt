package com.clothex.user.home.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.clothex.data.domain.model.body.ProductBody
import com.clothex.data.domain.model.body.ShopBody
import com.clothex.data.domain.model.body.SortEnum
import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.data.domain.model.home.HomeShop
import com.clothex.data.domain.usecases.filter.GetColorFilterUseCase
import com.clothex.data.domain.usecases.filter.GetPriceEndFilterUseCase
import com.clothex.data.domain.usecases.filter.GetPriceStartFilterUseCase
import com.clothex.data.domain.usecases.filter.GetSizeFilterUseCase
import com.clothex.data.domain.usecases.local.ClearFilterUseCase
import com.clothex.data.domain.usecases.product.GetProductPagingUseCase
import com.clothex.data.domain.usecases.shop.GetShopPageUseCase
import com.clothex.data.domain.usecases.sort.GetSortUseCase
import com.clothex.user.base.BaseLanguageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(
    private val shopPageUseCase: GetShopPageUseCase,
    private val getSortUseCase: GetSortUseCase,
    private val getSizeFilterUseCase: GetSizeFilterUseCase,
    private val getColorFilterUseCase: GetColorFilterUseCase,
    private val getPriceStartFilterUseCase: GetPriceStartFilterUseCase,
    private val getPriceEndFilterUseCase: GetPriceEndFilterUseCase,
    private val getProductPagingUseCase: GetProductPagingUseCase,
    private val clearFilterUseCase: ClearFilterUseCase
) : BaseLanguageViewModel() {

    val productLiveData = MutableLiveData<PagingData<HomeProduct>>()
    val shopLiveData = MutableLiveData<List<HomeShop>>()
    val loadingLiveData = MutableLiveData(false)
    val isEmptyLiveData = MutableLiveData(false)
    val isFilterApplied = MutableLiveData(false)
    val isSortApplied = MutableLiveData(false)

    var productPage: Int = 0
    var shopPage: Int = 0

    fun clearFilter() {
        viewModelScope.launch {
            clearFilterUseCase(Unit)
        }
    }

    fun reset() {
        productPage = 0
        shopPage = 0
        productLiveData.value = PagingData.empty()
        shopLiveData.value = listOf()
        loadingLiveData.postValue(true)
        isEmptyLiveData.postValue(false)
    }

    var shopId: String? = null
    var sort: String? = null
    var size: String? = null
    var color: String? = null
    var priceStart: Int? = null
    var priceEnd: Int? = null

    private suspend fun fetchLocalData() {
        sort = getSortUseCase(Unit).first()
        isSortApplied.postValue(sort != null && sort != SortEnum.BEST_MATCH.value)
        size = getSizeFilterUseCase(Unit).first()
        color = getColorFilterUseCase(Unit).first()
        priceStart = getPriceStartFilterUseCase(Unit).first()
        priceEnd = getPriceEndFilterUseCase(Unit).first()

        isFilterApplied.postValue(
            size.isNullOrEmpty().not() ||
                    color.isNullOrEmpty().not() ||
                    priceStart != null ||
                    priceEnd != null
        )
    }

    var isProducts = true

    fun fetch(search: String?) {
        if (isProducts)
            fetchProductPage(search)
        else
            fetchShopPage(search)
    }

    private fun fetchProductPage(search: String?) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                fetchLocalData()
                val body = ProductBody(
                    page = productPage,
                    sort = sort,
                    priceStart = priceStart,
                    priceEnd = priceEnd,
                    size = size,
                    shopId = shopId,
                    color = color,
                    search = search,
                    coroutineScope = viewModelScope
                )
                getProductPagingUseCase.invoke(body).cachedIn(viewModelScope).collect {
                    loadingLiveData.postValue(false)
                    productLiveData.postValue(it)
                }
            }
        }
    }

    private fun fetchShopPage(search: String?) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                shopPageUseCase.invoke(ShopBody(page = shopPage, search = search))
                    .collect {
                        loadingLiveData.postValue(false)
                        shopPage++
                        shopLiveData.postValue(it)
                        isEmptyLiveData.postValue(it.isNullOrEmpty())
                    }
            }
        }
    }
}