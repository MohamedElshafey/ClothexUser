package com.clothex.user.home.product_details

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clothex.shop.core.models.Item
import com.clothex.user.data.*

class ProductDetailsViewModel(private val item: Item) : ViewModel() {

    val mainImagesLiveData = MutableLiveData<List<Media>>()

    val colorsLiveData = MutableLiveData<List<Color>>(item.colors)

    val sellingPrice = "EGP ${item.sale_price ?: item.price}"

    val listPrice = if (item.sale_price != null) "EGP ${item.price}" else null

    val sizesLiveData = MutableLiveData<List<Size>>()

    val branchesLiveData = MutableLiveData<List<Branch>>()

    val sizeVisibility = ObservableField(false)

    val branchesVisibility = ObservableField(false)

    val title = ObservableField(item.oldTitle())

    val sku = ObservableField("SKU: ${item.sku}")

    val skuVisibility = ObservableField(!item.sku.isNullOrEmpty())

    val shopTitle = ObservableField("H & M")

    val shopLogoUrl =
        ObservableField("https://i.pinimg.com/originals/d7/af/32/d7af326b85e62c293dba7bad9f4f1757.jpg")

    init {
        setMainImages()
//        title.set(item.title())
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

    fun selectColor(colorCode: String) {
        val color = item.colors?.first { it.code.equals(colorCode) }
        val sizes = color?.sizes
        if (sizes != null) {
            sizes.let { sizesLiveData.value = it }
            sizeVisibility.set(true)
        } else {
            sizesLiveData.value = listOf()
            sizeVisibility.set(false)
        }
        branchesLiveData.value = listOf()
        branchesVisibility.set(false)
        val images = color?.images
        if (images == null) {
            setMainImages()
        } else {
            images.let { mainImagesLiveData.value = it }
        }

    }

    fun selectSize(size: Size) {
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