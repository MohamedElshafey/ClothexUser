package com.clothex.user.home.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.usecases.my_item.DeleteMyItemsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BookViewModel(private val deleteMyItemsUseCase: DeleteMyItemsUseCase) : ViewModel() {

    fun deleteMyItem(id: String, callback: (SimpleResponse) -> Unit) {
        viewModelScope.launch {
            deleteMyItemsUseCase(id).collect { response ->
                response?.let { callback.invoke(response) }
            }
        }
    }

}