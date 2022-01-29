package com.clothex.user.my_items.items

import com.clothex.user.data.my_items.MyItemGroup

interface MyItemCallback {
    fun onItemClicked(myItemGroup: MyItemGroup)
    fun deleteMyItemGroup(myItemGroup: MyItemGroup)
}