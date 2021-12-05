package com.clothex.user.customview

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.core.content.ContextCompat
import com.clothex.user.R
import com.google.android.material.chip.Chip

/**
 * Created by Mohamed Elshafey on 19/11/2021.
 */
class ChoiceChip @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Chip(context, attributeSet, defStyleAttr) {

    init {
        setTextColor(ContextCompat.getColor(context, R.color.chip_text_color))
        setChipBackgroundColorResource(R.color.chip_background)
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        setTextAppearance(R.style.FontSemiBold)
    }

}