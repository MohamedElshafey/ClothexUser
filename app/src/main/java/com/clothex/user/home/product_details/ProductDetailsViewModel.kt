package com.clothex.user.home.product_details

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clothex.shop.core.models.Item
import com.clothex.user.data.*

class ProductDetailsViewModel(private val item: Item) : ViewModel() {

    val mainImagesLiveData = MutableLiveData<List<Media>>()

    val colorsLiveData = MutableLiveData<List<Color>>(item.colors)

    val sellingPrice = item.sale_price ?: item.price

    val sellingPriceString = "EGP $sellingPrice"

    val listPrice = if (item.sale_price != null) "EGP ${item.price}" else null

    val sizesLiveData = MutableLiveData<List<Size>>()

    val branchesLiveData = MutableLiveData<List<Branch>>()

    val sizeVisibility = ObservableField(false)

    val branchesVisibility = ObservableField(false)

    val title = ObservableField(item.oldTitle())

    val sku = ObservableField("SKU: ${item.sku}")

    val skuVisibility = ObservableField(!item.sku.isNullOrEmpty())

    val quantityText = ObservableField("1")

    var quantity = 1
        set(value) {
            field = if (value > 9) 9 else value
            quantityText.set(value.toString())
        }

    val shopTitle = ObservableField("H & M")

    val shopAddress = ObservableField("New cairo, 5th settlement")

    val shopLogoUrl =
        ObservableField("https://i.pinimg.com/originals/d7/af/32/d7af326b85e62c293dba7bad9f4f1757.jpg")

    init {
        setMainImages()
    }

    private fun getSKU(): String {
        return "SKU: ${item.sku}"
    }

    private fun setMainImages() {
        val images = ArrayList<Media>()
        if (!item.images.isNullOrEmpty()) {
            images.addAll(item.images)
        }
        item.colors?.forEach {
            if (!it.images.isNullOrEmpty()) {
                images.addAll(it.images!!)
            }
        }

        mainImagesLiveData.value = images
    }

    var selectedColor: Color? = null

    fun selectColor(colorCode: String) {
        selectedColor = item.colors?.first { it.code.equals(colorCode) }
        val sizes = selectedColor?.sizes
        if (sizes != null) {
            sizes.let { sizesLiveData.value = it }
            sizeVisibility.set(true)
        } else {
            sizesLiveData.value = listOf()
            sizeVisibility.set(false)
        }
        branchesLiveData.value = listOf()
        branchesVisibility.set(false)
        val images = selectedColor?.images
        if (images == null) {
            setMainImages()
        } else {
            images.let { mainImagesLiveData.value = it }
        }
        selectedSize = null
    }

    var selectedSize: Size? = null

    fun selectSize(size: Size) {
        selectedSize = size
        val branches = size.available_branches
        if (!branches.isNullOrEmpty()) {
            branches.let { branchesLiveData.value = it }
            branchesVisibility.set(true)
        } else {
            branchesLiveData.value = listOf()
            branchesVisibility.set(false)
        }
    }

}