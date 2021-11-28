package com.clothex.user.home.product

import androidx.lifecycle.ViewModel
import com.clothex.shop.core.models.Item
import com.clothex.user.data.oldTitle
import com.clothex.user.data.pictureUrl

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class ProductItemViewModel(private val item: Item) : ViewModel() {

    val mainImageUrl = item.pictureUrl()

    val tag = item.tag

    val title = item.oldTitle()

    val price = "EGP ${item.sale_price ?: item.price}"

    val oldPrice = if (item.sale_price != null) "EGP ${item.price}" else null

    val discountText = calculateDiscount()

    private fun calculateDiscount(): String? {
        return try {
            val listPrice = item.price!!.toFloat()
            val sellingPrice = item.sale_price!!.toFloat()
            val priceInt = (listPrice - sellingPrice)
            val percentage: Float = ((listPrice - sellingPrice) / listPrice) * 100f
            "You save: EGP $priceInt (${percentage.toInt()}%)"
        } catch (e: Exception) {
            null
        }
    }
}