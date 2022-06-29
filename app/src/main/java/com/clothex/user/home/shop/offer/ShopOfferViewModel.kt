package com.clothex.user.home.shop.offer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.offer.Offer
import com.clothex.data.domain.usecases.offer.GetShopOffersUseCase
import com.clothex.user.base.BaseLanguageViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ShopOfferViewModel(
    private val getShopOffersUseCase: GetShopOffersUseCase
) : BaseLanguageViewModel() {

    val offersLiveData = MutableLiveData<List<Offer>>()

    fun fetchOffers(shopId: String) {
        viewModelScope.launch {
            getShopOffersUseCase.invoke(shopId).collect {
                offersLiveData.postValue(it)
            }
        }
    }

}