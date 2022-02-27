package com.clothex.user.customview.card

import androidx.annotation.LayoutRes
import com.clothex.user.R

enum class CardSize(@LayoutRes val layout: Int, val cornerRadius: Int) {
    SMALL(R.layout.small_card, 12),
    NORMAL(R.layout.card, 15)
}