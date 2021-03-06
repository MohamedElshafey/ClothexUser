package com.clothex.user.home.book

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.body.OrderBody
import com.clothex.data.domain.usecases.my_item.DeleteMyItemUseCase
import com.clothex.data.domain.usecases.order.CreateMyOrdersUseCase
import com.clothex.data.domain.usecases.sign.GetIsLoginTemporaryUseCase
import com.clothex.user.base.BaseLanguageViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BookViewModel(
    private val deleteMyItemUseCase: DeleteMyItemUseCase,
    private val createMyOrdersUseCase: CreateMyOrdersUseCase,
    private val getIsLoginTemporaryUseCase: GetIsLoginTemporaryUseCase
) : BaseLanguageViewModel() {

    val isLoginTemporaryLiveData = MutableLiveData<Boolean>()

    fun getIsLoginUser() {
        viewModelScope.launch {
            getIsLoginTemporaryUseCase(Unit).collect {
                isLoginTemporaryLiveData.postValue(it)
            }
        }
    }

    fun deleteMyItem(id: String, callback: (SimpleResponse) -> Unit) {
        viewModelScope.launch {
            deleteMyItemUseCase(id).collect { response ->
                response?.let { callback.invoke(response) }
            }
        }
    }

    fun createMyOrder(orderBody: OrderBody, callback: (SimpleResponse?) -> Unit) {
        viewModelScope.launch {
            createMyOrdersUseCase(orderBody).collect {
                callback.invoke(it)
            }
        }
    }

}