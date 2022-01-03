package com.clothex.user.home.product

import androidx.lifecycle.ViewModel
import com.clothex.data.domain.model.home.HomeProduct;

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class ProductItemViewModel(private val product: HomeProduct) : ViewModel() {

    val mainImageUrl = product.mainImage?.source

    val title = product.title

    val price = "EGP ${product.salePrice ?: product.price}"

    val oldPrice = if (product.salePrice != null) "EGP ${product.price}" else null

    val tag = product.tag

    val discountText = calculateDiscount()

    private fun calculateDiscount(): String? {
        return try {
            val listPrice = product.price.toFloat()
            val sellingPrice = product.salePrice!!.toFloat()
            val priceInt = (listPrice - sellingPrice)
            val percentage: Float = ((listPrice - sellingPrice) / listPrice) * 100f
            "You save: EGP $priceInt (${percentage.toInt()}%)"
        } catch (e: Exception) {
            null
        }
    }
}