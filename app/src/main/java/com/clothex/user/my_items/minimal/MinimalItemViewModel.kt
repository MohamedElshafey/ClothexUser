package com.clothex.user.my_items.minimal

import androidx.lifecycle.ViewModel
import com.clothex.data.domain.model.my_item.MyItem

class MinimalItemViewModel(myItem: MyItem) : ViewModel() {

    val title = myItem.product.title

    val imageUrl = myItem.product.mainImage?.source

    val colorCode = myItem.colorCode

    val sizeName = myItem.sizeName

    val quantity = myItem.quantity.toString()

    val totalPrice = myItem.product.price.toFloat() * myItem.quantity.toFloat()

}