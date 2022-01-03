package com.clothex.user.home.search.filter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.usecases.filter.*
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
    private val getPriceEndFilterUseCase: GetPriceEndFilterUseCase
) : ViewModel() {

    val sizeMutableLiveData = MutableLiveData<String?>()
    val colorMutableLiveData = MutableLiveData<String?>()
    val priceStartMutableLiveData = MutableLiveData<Int?>()
    val priceEndMutableLiveData = MutableLiveData<Int?>()

    var selectedColor: String? = null
    var selectedSize: String? = null
    var selectedPriceStart: Int? = null
    var selectedPriceEnd: Int? = null

    init {
        getSizeFilter()
        getColorFilter()
        getPriceStartFilter()
        getPriceEndFilter()
    }

    fun setSizeFilter(size: String) = viewModelScope.launch {
        setSizeFilterUseCase(size)
        getSizeFilter()
    }

    fun getSizeFilter() = viewModelScope.launch {
        getSizeFilterUseCase(Unit).collect {
            sizeMutableLiveData.postValue(it)
        }
    }

    fun setColorFilter(color: String) = viewModelScope.launch {
        setColorFilterUseCase(color)
        getColorFilter()
    }

    fun getColorFilter() = viewModelScope.launch {
        getColorFilterUseCase(Unit).collect {
            colorMutableLiveData.postValue(it)
        }
    }

    fun setPriceStartFilter(price: Int) = viewModelScope.launch {
        setPriceStartFilterUseCase(price)
        getPriceStartFilter()
    }

    fun getPriceStartFilter() = viewModelScope.launch {
        getPriceStartFilterUseCase(Unit).collect {
            priceStartMutableLiveData.postValue(it)
        }
    }

    fun setPriceEndFilter(price: Int) = viewModelScope.launch {
        setPriceEndFilterUseCase(price)
        getPriceEndFilter()
    }

    fun getPriceEndFilter() = viewModelScope.launch {
        getPriceEndFilterUseCase(Unit).collect {
            priceEndMutableLiveData.postValue(it)
        }
    }

}