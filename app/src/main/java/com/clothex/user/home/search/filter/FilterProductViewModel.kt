package com.clothex.user.home.search.filter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.usecases.filter.*
import com.clothex.data.domain.usecases.local.ClearFilterUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FilterProductViewModel(
    private val setSizeFilterUseCase: SetSizeFilterUseCase,
    private val getSizeFilterUseCase: GetSizeFilterUseCase,
    private val setColorFilterUseCase: SetColorFilterUseCase,
    private val getColorFilterUseCase: GetColorFilterUseCase,
    private val getPriceStartFilterUseCase: GetPriceStartFilterUseCase,
    private val setPriceStartFilterUseCase: SetPriceStartFilterUseCase,
    private val setPriceEndFilterUseCase: SetPriceEndFilterUseCase,
    private val getPriceEndFilterUseCase: GetPriceEndFilterUseCase,
    private val clearFilterUseCase: ClearFilterUseCase
) : ViewModel() {

    private val priceDefaultValueFrom = 0
    private val priceDefaultValueTo = 5000

    val sizeMutableLiveData = MutableLiveData<String?>()
    val colorMutableLiveData = MutableLiveData<String?>()
    val priceStartMutableLiveData = MutableLiveData<Int?>()
    val priceEndMutableLiveData = MutableLiveData<Int?>()

    var selectedColor: String? = null
    var selectedSize: String? = null
    var selectedPriceStart: Int? = null
    var selectedPriceEnd: Int? = null

    var filterAppliedMutableLiveData = MutableLiveData(false)

    init {
        getSizeFilter()
        getColorFilter()
        getPriceStartFilter()
        getPriceEndFilter()
        fetchFilterData()
    }

    fun setSizeFilter(size: String?) = viewModelScope.launch {
        setSizeFilterUseCase.invoke(size)
        getSizeFilter()
    }

    fun getSizeFilter() = viewModelScope.launch {
        getSizeFilterUseCase(Unit).collect {
            sizeMutableLiveData.value = it
            selectedSize = it
            fetchFilterData()
        }
    }

    fun setColorFilter(color: String?) = viewModelScope.launch {
        setColorFilterUseCase(color)
        getColorFilter()
    }

    fun getColorFilter() = viewModelScope.launch {
        getColorFilterUseCase(Unit).collect {
            colorMutableLiveData.value = it
            selectedColor = it
            fetchFilterData()
        }
    }

    fun setPriceStartFilter(price: Int?) = viewModelScope.launch {
        if (price != priceDefaultValueFrom) {
            setPriceStartFilterUseCase(price)
        } else {
            setPriceStartFilterUseCase(null)
        }
        getPriceStartFilter()
    }

    fun getPriceStartFilter() = viewModelScope.launch {
        getPriceStartFilterUseCase(Unit).collect {
            priceStartMutableLiveData.value = it
            selectedPriceStart = it
            fetchFilterData()
        }
    }

    fun setPriceEndFilter(price: Int?) = viewModelScope.launch {
        if (price != priceDefaultValueTo) {
            setPriceEndFilterUseCase(price)
        } else {
            setPriceEndFilterUseCase(null)
        }
        getPriceEndFilter()
    }

    fun getPriceEndFilter() = viewModelScope.launch {
        getPriceEndFilterUseCase(Unit).collect {
            priceEndMutableLiveData.value = it
            selectedPriceEnd = it
            fetchFilterData()
        }
    }

    private fun fetchFilterData() {
        val size = sizeMutableLiveData.value
        val color = colorMutableLiveData.value
        val priceStart = priceStartMutableLiveData.value
        val priceEnd = priceEndMutableLiveData.value
        filterAppliedMutableLiveData.value = color.isNullOrBlank().not() ||
                size.isNullOrBlank().not() ||
                (priceStart != null && priceStart != priceDefaultValueFrom) ||
                (priceEnd != null && priceEnd != priceDefaultValueTo)
    }

    fun resetFilter() {
        selectedColor = null
        selectedSize = null
        selectedPriceStart = null
        selectedPriceEnd = null
        viewModelScope.launch {
            clearFilterUseCase(Unit)
        }
        filterAppliedMutableLiveData.value = false
    }

}