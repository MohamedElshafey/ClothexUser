package com.clothex.user.utils

import android.content.res.Resources
import android.util.TypedValue

fun Float.toDP(resources: Resources): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, resources.displayMetrics)
        .toInt()
}