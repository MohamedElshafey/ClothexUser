package com.clothex.user.home.image

import android.view.ViewGroup

/**
 * Created by Mohamed Elshafey on 21/12/2021.
 */
enum class ImageSize(val width: Int, val height: Int, val cornerRadius: Float) {
    FULL_SIZE(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT,
        0f
    ),
    SMALL_SQUARE(70, 70, 15f)
}