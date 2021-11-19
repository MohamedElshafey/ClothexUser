package com.clothex.user.utils

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils

/**
 * Created by Mohamed Elshafey on 19/11/2021.
 */

val @receiver:ColorInt Int.darken
    @ColorInt
    get() = ColorUtils.blendARGB(this, Color.BLACK, 0.5f)
