package com.clothex.user.home.checkout

import androidx.lifecycle.ViewModel
import com.clothex.user.data.my_items.MinimalProduct

class MyItemViewModel(minimalProduct: MinimalProduct) : ViewModel() {

    val title = minimalProduct.title

    val imageUrl = minimalProduct.image?.source

    val colorCode = minimalProduct.colorCode

    val sizeName = minimalProduct.sizeName

    val quantity = minimalProduct.quantity.toString()

    val price = "EGP ${minimalProduct.price * minimalProduct.quantity}"

}