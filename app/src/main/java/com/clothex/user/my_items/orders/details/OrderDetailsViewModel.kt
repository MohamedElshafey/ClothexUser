package com.clothex.user.my_items.orders.details

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.order.MyOrder
import com.clothex.data.domain.usecases.order.GetOrderDetailsUseCase
import com.clothex.user.base.BaseLanguageViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OrderDetailsViewModel(private val getOrderDetailsUseCase: GetOrderDetailsUseCase) :
    BaseLanguageViewModel() {

    val shopLogo = ObservableField<String>()
    val shopName = ObservableField<String>()
    val branchAddressName = ObservableField<String>()
    val orderMutableLiveData = MutableLiveData<Result<MyOrder>>()
    fun fetchOrderDetails(orderId: String) {
        viewModelScope.launch {
            getOrderDetailsUseCase.invoke(orderId).collect {
                orderMutableLiveData.postValue(it)
                it.getOrNull()?.let { order ->
                    with(order) {
                        with(shop) {
                            shopLogo.set(logo?.source)
                            shopName.set(getName(isArabic()))
                        }
                        branchAddressName.set(branch.address?.name)
                    }
                }
            }
        }
    }

}