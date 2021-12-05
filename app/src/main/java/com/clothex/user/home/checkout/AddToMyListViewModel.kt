package com.clothex.user.home.checkout

import androidx.lifecycle.ViewModel
import com.clothex.user.data.my_items.MyItem

class AddToMyListViewModel(val myItem: MyItem) : ViewModel() {

    val title = myItem.title

    val imageUrl = myItem.image?.source

    val colorCode = myItem.colorCode

    val sizeName = myItem.sizeName

    val quantity = myItem.quantity.toString()

    val price = "EGP ${myItem.price * myItem.quantity}"

}