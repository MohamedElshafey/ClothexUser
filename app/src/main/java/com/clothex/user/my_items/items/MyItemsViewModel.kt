package com.clothex.user.my_items.items

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.my_item.MyItem
import com.clothex.data.domain.usecases.my_item.GetMyItemsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MyItemsViewModel(private val getMyItemsUseCase: GetMyItemsUseCase) : ViewModel() {

    val mutableMyItemsLiveData: MutableLiveData<List<MyItem>?> = MutableLiveData()


    fun fetchMyItems() {
        viewModelScope.launch {
            getMyItemsUseCase(Unit).collect {
                mutableMyItemsLiveData.postValue(it)
            }
        }
    }

}