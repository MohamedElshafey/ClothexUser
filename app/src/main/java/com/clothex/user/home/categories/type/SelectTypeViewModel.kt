package com.clothex.user.home.categories.type

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.body.ProductBody
import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.data.domain.usecases.product.GetProductPageUseCase
import com.clothex.user.base.BaseLanguageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectTypeViewModel(private val productPageUseCase: GetProductPageUseCase) :
    BaseLanguageViewModel() {

    var productPage: Int = 0
    val productLiveData = MutableLiveData<List<HomeProduct>>()

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
                productPageUseCase.invoke(
                    ProductBody(
                        page = 0,
                        department = departmentId,
                        type = typeId,
                        search = searchKey
                    )
                ).collect {
                    productPage++
                    productLiveData.postValue(it)
                }
            }
        }
    }
}