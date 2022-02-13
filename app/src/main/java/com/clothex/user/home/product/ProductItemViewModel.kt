package com.clothex.user.home.product

import androidx.lifecycle.ViewModel
import com.clothex.data.domain.model.home.HomeProduct;
import kotlin.math.roundToInt

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class ProductItemViewModel(private val product: HomeProduct) : ViewModel() {

    init {
        calculateDiscount()
    }

    val mainImageUrl = product.mainImage?.source

    val title = product.title

    val totalPrice = product.salePrice ?: product.price

    val oldPrice = if (product.salePrice != null) product.price else null

    val tag = product.tag

    var savedPercentage = 0

    var savedAmount = 0

    private fun calculateDiscount() {
        try {
            val listPrice = product.price.toFloat()
            val sellingPrice = product.salePrice!!.toFloat()
            savedAmount = ((listPrice - sellingPrice).roundToInt())
            savedPercentage = (((listPrice - sellingPrice) / listPrice) * 100f).roundToInt()
        } catch (e: Exception) {
        }
    }
}