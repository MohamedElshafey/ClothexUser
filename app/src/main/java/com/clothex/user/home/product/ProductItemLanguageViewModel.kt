package com.clothex.user.home.product

import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.user.base.BaseLanguageViewModel
import kotlin.math.roundToInt

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class ProductItemLanguageViewModel(private val product: HomeProduct) : BaseLanguageViewModel() {


    var savedAmount = calculateSavedAmount()

    var savedPercentage = calculateSavedPercentage()

    val mainImageUrl = product.mainImage?.source

    val title = product.getTitle(isArabic())

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