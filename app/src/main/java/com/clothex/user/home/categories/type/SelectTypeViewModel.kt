package com.clothex.user.home.categories.type

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.clothex.data.domain.model.body.ProductBody
import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.data.domain.usecases.product.GetProductPagingUseCase
import com.clothex.user.base.BaseLanguageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectTypeViewModel(private val productPagingUseCase: GetProductPagingUseCase) :
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


    fun reset() {
        productPage = 0
    }

    private fun fetchProductPage() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                productPagingUseCase.invoke(
                    ProductBody(
                        page = 0,
                        department = departmentId,
                        type = typeId,
                        search = searchKey,
                        coroutineScope = viewModelScope
                    )
                ).collect {
                    productPage++
                    productLiveData.postValue(it)
                }
            }
        }
    }
}