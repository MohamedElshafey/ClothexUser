package com.clothex.user.data

import androidx.annotation.DrawableRes

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
data class SortItem(val title: String, @DrawableRes val iconRes: Int, var isSelected: Boolean)
