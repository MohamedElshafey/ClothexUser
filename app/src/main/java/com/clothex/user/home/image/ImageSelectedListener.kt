package com.clothex.user.home.image

import com.clothex.data.domain.model.product.Media

interface ImageSelectedListener {
    fun onImageSelected(mediaList: List<Media>, selectedIndex: Int)
}