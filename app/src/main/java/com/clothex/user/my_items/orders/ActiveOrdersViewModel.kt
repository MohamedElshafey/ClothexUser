package com.clothex.user.my_items.orders

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.order.MyOrder
import com.clothex.data.domain.usecases.order.GetMyOrdersUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ActiveOrdersViewModel(private val getMyOrdersUseCase: GetMyOrdersUseCase) : ViewModel() {

    val myOrdersLiveData = MutableLiveData<List<MyOrder>?>()

    fun fetchMyOrders(customerId: String) {
        viewModelScope.launch {
            getMyOrdersUseCase(customerId).collect {
                myOrdersLiveData.postValue(it)
            }
        }
    }

}