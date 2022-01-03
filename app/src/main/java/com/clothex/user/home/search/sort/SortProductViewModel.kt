package com.clothex.user.home.search.sort

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.body.SortEnum
import com.clothex.data.domain.usecases.sort.GetSortUseCase
import com.clothex.data.domain.usecases.sort.SetSortUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SortProductViewModel(
    private val setSortUseCase: SetSortUseCase,
    private val getSortUseCase: GetSortUseCase
) : ViewModel() {

    val sortMutableLiveData = MutableLiveData<String?>()

    init {
        getSort()
    }

    fun getSort() = viewModelScope.launch {
        getSortUseCase(Unit).collect {
            sortMutableLiveData.postValue(it)
        }
    }

    fun setSort(sortEnum: SortEnum) = viewModelScope.launch {
        setSortUseCase(sortEnum)
    }

}