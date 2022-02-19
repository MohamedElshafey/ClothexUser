package com.clothex.user.home.shop.details

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.product.Media
import com.clothex.data.domain.model.shop.Shop
import com.clothex.data.domain.usecases.shop.GetShopDetailsUseCase
import com.clothex.user.base.BaseLanguageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Mohamed Elshafey on 11/12/2021.
 */
class ShopDetailsViewModel(private val getShopDetailsUseCase: GetShopDetailsUseCase) :
    BaseLanguageViewModel() {

    val shopMutableLiveData = MutableLiveData<Shop>()

    fun fetchDetails(shopId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getShopDetailsUseCase(shopId).collect { shop ->
                    shopMutableLiveData.postValue(shop)
                    updateShopData(shop)
                }
            }
        }
    }

    private fun updateShopData(shop: Shop?) {
        imageUrl.set(shop?.logo?.source)
        shopName.set(shop?.getName(isArabic()))
    }

    val imageUrl = ObservableField("")
    val shopName = ObservableField("")
    val shopAddress = ObservableField("")
    val shopPhotos = ObservableField<List<Media>>()
    val addressUrl = ObservableField(
        "https://maps.googleapis.com/maps/api/staticmap?center=40.714728,-73.998672&zoom=14&size=600x300"
    )
}