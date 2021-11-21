package com.clothex.user.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.clothex.user.R

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class BottomSheetContainer @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr) {

    init {
        orientation = VERTICAL
        inflate(context, R.layout.bottom_sheet_container, this)
    }
}