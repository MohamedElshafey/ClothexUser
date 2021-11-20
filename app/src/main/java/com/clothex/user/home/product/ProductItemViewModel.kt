package com.clothex.user.home.product

import androidx.lifecycle.ViewModel
import com.clothex.user.data.Product

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class ProductItemViewModel(private val product: Product) : ViewModel() {

    val mainImageUrl = product.imageUrl

    val tag = product.tag

    val title = product.title

    val price = "EGP ${product.price}"

    val oldPrice = if (product.oldPrice.isNullOrEmpty().not()) "EGP ${product.oldPrice}" else null

    val discountText = calculateDiscount()

    private fun calculateDiscount(): String? {
        return try {
            val listPrice = product.oldPrice!!.toFloat()
            val sellingPrice = product.price.toFloat()
            val priceInt = (listPrice - sellingPrice).toInt()
            val percentage: Float = ((listPrice - sellingPrice) / listPrice) * 100f
            "You save: EGP $priceInt (${percentage.toInt()}%)"
        } catch (e: Exception) {
            null
        }
    }

}