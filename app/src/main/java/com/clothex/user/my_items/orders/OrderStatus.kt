package com.clothex.user.my_items.orders

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import com.clothex.user.R

enum class OrderStatus(
    val value: Int,
    @StringRes val title: Int,
    @ColorInt val contentColor: Int,
    @ColorInt val backgroundColor: Int
) {
    ACTIVE(1, R.string.active, Color.parseColor("#10c935"), Color.parseColor("#1A10c935")),
    PENDING(0, R.string.pending, Color.parseColor("#974b00"), Color.parseColor("#ffedaf")),
    REJECT(2, R.string.rejected, Color.parseColor("#970000"), Color.parseColor("#ffdfdf"))
}