package com.clothex.user.offer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.offer.Offer
import com.clothex.data.domain.usecases.offer.GetOffersUseCase
import com.clothex.user.base.BaseLanguageViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OfferViewModel(
    private val getOffersUseCase: GetOffersUseCase
) : BaseLanguageViewModel() {

    val vouchersLiveData = MutableLiveData<List<Offer>>()
    fun fetchOffers() {
        viewModelScope.launch {
            getOffersUseCase.invoke(Unit).collect {
                vouchersLiveData.postValue(it)
            }
        }
    }

}