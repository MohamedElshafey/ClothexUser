package com.clothex.user.my_items.orders

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.order.MyOrder
import com.clothex.data.domain.usecases.order.GetMyOrdersUseCase
import com.clothex.user.base.BaseLanguageViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ActiveOrdersViewModel(private val getMyOrdersUseCase: GetMyOrdersUseCase) :
    BaseLanguageViewModel() {

    val myOrdersLiveData = MutableLiveData<List<MyOrder>?>()

    fun fetchMyOrders() {
        viewModelScope.launch {
            getMyOrdersUseCase(Unit).collect {
                myOrdersLiveData.postValue(it)
            }
        }
    }

}