package com.clothex.user.home.product_details

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.product.*
import com.clothex.data.domain.usecases.product.GetProductDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductDetailsViewModel(private val getProductDetailsUseCase: GetProductDetailsUseCase) :
    ViewModel() {

    val productMutableLiveData = MutableLiveData<Product?>()
    val sellingPrice = ObservableField<Int>()
    val colorsLiveData = MutableLiveData<List<Color>>()
    val mainImagesLiveData = MutableLiveData<List<Media>>()
    val sellingPriceString = ObservableField<String>()
    val listPrice = ObservableField<String>()
    val sizesLiveData = MutableLiveData<List<Size>>()
    val branchesLiveData = MutableLiveData<List<Branch>>()
    val sizeVisibility = ObservableField(false)
    val branchesVisibility = ObservableField(false)
    val title = ObservableField<String>()

    //    val sku = ObservableField<String>()
//    val skuVisibility = ObservableField<Boolean>()
    val quantityText = ObservableField("1")
    val shopTitle = ObservableField<String>()
    val shopAddress = ObservableField("New cairo, 5th settlement")
    val shopLogoUrl = ObservableField<String>()

    var product: Product? = null

    fun getProductDetails(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getProductDetailsUseCase.invoke(id).collect {
                    productMutableLiveData.postValue(it)
                    product = it
                    updateProductData(it)
                }
            }
        }
    }

    private fun updateProductData(product: Product?) {
        sellingPrice.set(product?.salePrice ?: product?.price)
        listPrice.set(if (product?.salePrice != null) "EGP ${product.price}" else null)
        colorsLiveData.postValue(product?.colors!!)
        sellingPriceString.set("EGP ${sellingPrice.get()}")
        title.set(product.title)
//        sku.set("SKU: ${product.sku}")
//        skuVisibility.set(!product.sku.isNullOrEmpty())
        setDefaultImages()
        shopTitle.set(product.shop?.name)
        shopLogoUrl.set(product.shop?.logo?.source)
    }

    var quantity = 1
        set(value) {
            field = if (value > 9) 9 else value
            quantityText.set(value.toString())
        }

    private fun setDefaultImages() {
        val images = ArrayList<Media>()
        product?.colors?.forEach {
            if (!it.images.isNullOrEmpty()) {
                images.addAll(it.images!!)
            }
        }
        mainImagesLiveData.postValue(images)
    }

    var selectedColor: Color? = null

    fun selectColor(colorCode: String) {
        val item = productMutableLiveData.value
        selectedColor = item?.colors?.first { it.code.equals(colorCode) }
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
            setDefaultImages()
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