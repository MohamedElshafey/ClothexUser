package com.clothex.user.my_items.minimal

import androidx.lifecycle.ViewModel
import com.clothex.data.domain.model.my_item.MyItem

class MinimalItemViewModel(minimalProduct: MyItem) : ViewModel() {

    val title = minimalProduct.product.title

    val imageUrl = minimalProduct.product.mainImage?.source

    val colorCode = minimalProduct.colorCode

    val sizeName = minimalProduct.sizeName

    val quantity = minimalProduct.quantity.toString()

    val price = "EGP ${minimalProduct.product.price * minimalProduct.quantity}"

}