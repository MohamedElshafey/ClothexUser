package com.clothex.user.home.categories.type

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.clothex.data.domain.model.body.ProductBody
import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.data.domain.usecases.filter.GetColorFilterUseCase
import com.clothex.data.domain.usecases.filter.GetPriceEndFilterUseCase
import com.clothex.data.domain.usecases.filter.GetPriceStartFilterUseCase
import com.clothex.data.domain.usecases.filter.GetSizeFilterUseCase
import com.clothex.data.domain.usecases.product.GetProductPagingUseCase
import com.clothex.data.domain.usecases.sort.GetSortUseCase
import com.clothex.user.base.BaseLanguageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectTypeViewModel(
    private val getSortUseCase: GetSortUseCase,
    private val getSizeFilterUseCase: GetSizeFilterUseCase,
    private val getColorFilterUseCase: GetColorFilterUseCase,
    private val getPriceStartFilterUseCase: GetPriceStartFilterUseCase,
    private val getPriceEndFilterUseCase: GetPriceEndFilterUseCase,
    private val productPagingUseCase: GetProductPagingUseCase
) :
    BaseLanguageViewModel() {

    var productPage: Int = 0
    val productLiveData = MutableLiveData<PagingData<HomeProduct>>()

    var departmentId: String? = null
        set(value) {
            field = value
            fetchProductPage()
        }

    var typeId: String? = null
        set(value) {
            field = value
            fetchProductPage()
        }

    var searchKey: String? = null
        set(value) {
            field = value
            fetchProductPage()
        }

    private var sort: String? = null
    private var size: String? = null
    private var color: String? = null
    private var priceStart: Int? = null
    private var priceEnd: Int? = null

    private suspend fun fetchLocalData() {
        sort = getSortUseCase(Unit).first()
        size = getSizeFilterUseCase(Unit).first()
        color = getColorFilterUseCase(Unit).first()
        priceStart = getPriceStartFilterUseCase(Unit).first()
        priceEnd = getPriceEndFilterUseCase(Unit).first()
    }


    fun reset() {
        productPage = 0
    }

    fun fetchProductPage() {
        productPage = 0
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                fetchLocalData()
                productPagingUseCase.invoke(
                    ProductBody(
                        page = 0,
                        department = departmentId,
                        type = typeId,
                        search = searchKey,
                        sort = sort,
                        size = size,
                        priceStart = priceStart,
                        priceEnd = priceEnd,
                        color = color,
                        coroutineScope = viewModelScope
                    )
                ).cachedIn(viewModelScope).collect {
                    productPage++
                    productLiveData.postValue(it)
                }
            }
        }
    }
}