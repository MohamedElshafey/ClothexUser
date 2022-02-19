package com.clothex.user.home.product_details

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.body.MyItemBody
import com.clothex.data.domain.model.my_item.MyItem
import com.clothex.data.domain.model.product.*
import com.clothex.data.domain.usecases.my_item.CreateMyItemsUseCase
import com.clothex.data.domain.usecases.product.GetProductDetailsUseCase
import com.clothex.user.base.BaseLanguageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductDetailsViewModel(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val createMyItemsUseCase: CreateMyItemsUseCase
) : BaseLanguageViewModel() {

    val productMutableLiveData = MutableLiveData<Product?>()

    val colorsLiveData = MutableLiveData<List<Color>>()
    val mainImagesLiveData = MutableLiveData<List<Media>>()
    val sellingPriceString = ObservableField<String>()
    val sizesLiveData = MutableLiveData<List<Size>>()
    val branchesLiveData = MutableLiveData<List<Branch>>()
    val colorVisibility = ObservableField(false)
    val sizeVisibility = ObservableField(false)
    val branchesVisibility = ObservableField(false)
    val showViewsVisibility = ObservableField(false)
    val quantityText = ObservableField("1")
    val shopLogoUrl = ObservableField<String>()
    var product: Product? = null

    fun getProductDetails(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getProductDetailsUseCase.invoke(id).collect {
                    productMutableLiveData.postValue(it)
                    product = it
                    colorsLiveData.postValue(product?.colors!!)
                    setDefaultImages()
                    if (it != null)
                        showViewsVisibility.set(true)
                }
            }
        }
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
        selectedBranch = null
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
        selectedBranch = null
    }

    var selectedBranch: Branch? = null
    fun selectBranch(branch: Branch?) {
        selectedBranch = branch
    }

    fun addToMyItem(myItemBody: MyItemBody, callback: (MyItem) -> Unit) {
        viewModelScope.launch {
            createMyItemsUseCase(myItemBody).collect { response ->
                response?.let { callback(response) }
            }
        }
    }


}