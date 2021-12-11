package com.clothex.user.home.shop.details

import androidx.lifecycle.ViewModel
import com.clothex.user.data.Shop

/**
 * Created by Mohamed Elshafey on 11/12/2021.
 */
class ShopDetailsViewModel(shop: Shop) : ViewModel() {

    val imageUrl = shop.logoUrl
    val shopName = shop.name
    val shopAddress = shop.addressName
    val addressUrl =
        "https://maps.googleapis.com/maps/api/staticmap?center=40.714728,-73.998672&zoom=14&size=600x300"
}