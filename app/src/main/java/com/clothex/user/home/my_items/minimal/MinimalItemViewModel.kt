package com.clothex.user.home.my_items.minimal

import androidx.lifecycle.ViewModel
import com.clothex.user.data.my_items.MinimalProduct

class MinimalItemViewModel(minimalProduct: MinimalProduct) : ViewModel() {

    val title = minimalProduct.title

    val imageUrl = minimalProduct.image?.source

    val colorCode = minimalProduct.colorCode

    val sizeName = minimalProduct.sizeName

    val quantity = minimalProduct.quantity.toString()

    val price = "EGP ${minimalProduct.price * minimalProduct.quantity}"

}