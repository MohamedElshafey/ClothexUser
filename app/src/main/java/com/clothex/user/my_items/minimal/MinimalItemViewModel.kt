package com.clothex.user.my_items.minimal

import com.clothex.data.domain.model.my_item.MyItem
import com.clothex.user.base.BaseLanguageViewModel

class MinimalItemViewModel(myItem: MyItem) : BaseLanguageViewModel() {

    val title = myItem.product.getTitle(isArabic())

    val imageUrl = myItem.product.mainImage?.source

    val colorCode = myItem.colorCode

    val sizeName = myItem.sizeName

    val quantity = myItem.quantity.toString()

    val totalPrice = myItem.product.price.toFloat() * myItem.quantity.toFloat()

}