package com.clothex.user.my_items.items

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.my_item.MyItem
import com.clothex.data.domain.usecases.my_item.DeleteMyItemsUseCase
import com.clothex.data.domain.usecases.my_item.GetMyItemsUseCase
import com.clothex.user.base.BaseLanguageViewModel
import com.clothex.user.data.my_items.MyItemGroup
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MyItemsViewModel(
    private val getMyItemsUseCase: GetMyItemsUseCase,
    private val deleteMyItemsUseCase: DeleteMyItemsUseCase
) : BaseLanguageViewModel() {

    val mutableMyItemsLiveData: MutableLiveData<List<MyItem>?> = MutableLiveData()

    fun fetchMyItems() {
        viewModelScope.launch {
            getMyItemsUseCase(Unit).collect {
                mutableMyItemsLiveData.postValue(it)
            }
        }
    }

    fun deleteMyItemGroup(myItemGroup: MyItemGroup, callback: (SimpleResponse?) -> Unit) {
        viewModelScope.launch {
            val idArray = myItemGroup.myItems.map { it.id }
            deleteMyItemsUseCase.invoke(idArray).collect {
                callback.invoke(it)
            }
        }
    }

}