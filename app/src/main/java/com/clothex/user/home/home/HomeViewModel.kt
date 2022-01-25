package com.clothex.user.home.home

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.data.domain.model.home.HomeShop
import com.clothex.data.domain.usecases.home.GetHomeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val homeUseCase: GetHomeUseCase) : ViewModel() {

    val notificationCount = ObservableField("5")
    val productLiveData = MutableLiveData<List<HomeProduct>>()
    val shopLiveData = MutableLiveData<List<HomeShop>>()
    val failureLiveData = MutableLiveData<String>()

    init {
        fetchHome()
    }

    fun fetchHome() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                homeUseCase.invoke(Unit).collect { homeResult ->
                    homeResult.getOrNull()?.let {
                        productLiveData.postValue(it.products)
                        shopLiveData.postValue(it.shops)
                    }
                    homeResult.exceptionOrNull()?.let {
                        failureLiveData.postValue(it.message)
                    }
                }
            }
        }
    }
}