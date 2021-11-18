package com.clothex.user.onboarding.boarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Created by Mohamed Elshafey on 18/11/2021.
 */
data class OnBoardingModel(
    @DrawableRes val imageRes: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
)
