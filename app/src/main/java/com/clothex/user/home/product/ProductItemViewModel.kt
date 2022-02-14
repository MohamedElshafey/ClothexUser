package com.clothex.user.home.product

import androidx.lifecycle.ViewModel
import com.clothex.data.domain.model.home.HomeProduct;
import kotlin.math.roundToInt

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class ProductItemViewModel(private val product: HomeProduct) : ViewModel() {


    var savedAmount = calculateSavedAmount()

    var savedPercentage = calculateSavedPercentage()

    val mainImageUrl = product.mainImage?.source

    val title = product.title

    val totalPrice = product.salePrice ?: product.price

    val oldPrice = if (product.salePrice != null) product.price else null

    val tag = product.tag

    private fun calculateSavedAmount(): Int? = try {
        val listPrice = product.price.toFloat()
        val sellingPrice = product.salePrice!!.toFloat()
        (listPrice - sellingPrice).roundToInt()
    } catch (e: Exception) {
        null
    }

    private fun calculateSavedPercentage(): Int? = try {
        val listPrice = product.price.toFloat()
        savedAmount?.div(listPrice)?.times(100f)?.roundToInt()
    } catch (e: Exception) {
        null
    }
}