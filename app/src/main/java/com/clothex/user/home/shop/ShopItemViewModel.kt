package com.clothex.user.home.shop

import androidx.lifecycle.ViewModel
import com.clothex.user.data.Shop

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class ShopItemViewModel(private val shop: Shop) : ViewModel() {

    val logoUrl = shop.logoUrl

    val name = shop.name

    val address = shop.addressName

    val workingHour = shop.workingHour
}