package com.clothex.user.home.categories.style

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.clothex.user.R

sealed class DepartmentStyle(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    @ColorRes val backgroundColor: Int
) {

    object Men :
        DepartmentStyle(
            title = R.string.men,
            icon = R.drawable.ic_category_men,
            backgroundColor = R.color.nevada_sky
        )

    object Women :
        DepartmentStyle(
            title = R.string.women,
            icon = R.drawable.ic_category_women,
            backgroundColor = R.color.beauty_bush
        )

    object Kids :
        DepartmentStyle(
            title = R.string.kids,
            icon = R.drawable.ic_category_kids,
            backgroundColor = R.color.butter_milk
        )

}
