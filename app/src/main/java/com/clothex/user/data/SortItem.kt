package com.clothex.user.data

import androidx.annotation.DrawableRes
import com.clothex.data.domain.model.body.SortEnum

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
data class SortItem(
    val title: String,
    @DrawableRes val iconRes: Int,
    var isSelected: Boolean,
    val sortEnum: SortEnum
)
