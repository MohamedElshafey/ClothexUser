package com.clothex.user.home.book

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.body.OrderBody
import com.clothex.data.domain.usecases.my_item.DeleteMyItemsUseCase
import com.clothex.data.domain.usecases.order.CreateMyOrdersUseCase
import com.clothex.data.domain.usecases.sign.GetIsLoginTemporaryUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BookViewModel(
    private val deleteMyItemsUseCase: DeleteMyItemsUseCase,
    private val createMyOrdersUseCase: CreateMyOrdersUseCase,
    private val getIsLoginTemporaryUseCase: GetIsLoginTemporaryUseCase

) : ViewModel() {

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
            deleteMyItemsUseCase(id).collect { response ->
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