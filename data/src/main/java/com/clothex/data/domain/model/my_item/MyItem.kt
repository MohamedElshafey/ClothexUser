package com.clothex.data.domain.model.my_item

import android.os.Parcelable
import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.data.domain.model.home.HomeShop
import com.clothex.data.domain.model.product.Branch
import com.google.gson.annotations.SerializedName

@kotlinx.parcelize.Parcelize
data class MyItem(
    @SerializedName("_id")
    val id: String,
    val branch: Branch,
    @SerializedName("color_code")
    val colorCode: String,
    @SerializedName("customer_id")
    val customerId: String,
    val product: HomeProduct,
    val quantity: Int,
    @SerializedName("size_name")
    val sizeName: String,
    val shop: HomeShop
) : Parcelable