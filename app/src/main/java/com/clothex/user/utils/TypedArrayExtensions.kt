package com.clothex.user.utils

import android.content.res.TypedArray

/**
 * Created by Mohamed Elshafey on 13/09/2021.
 */

inline fun <reified T : Enum<T>> TypedArray.getEnum(index: Int, default: T) =
    getInt(index, -1).let {
        if (it >= 0) enumValues<T>()[it] else default
    }