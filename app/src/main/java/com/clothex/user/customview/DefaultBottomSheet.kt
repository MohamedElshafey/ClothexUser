package com.clothex.user.customview

import com.clothex.user.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Created by Mohamed Elshafey on 12/10/2021.
 */
open class DefaultBottomSheet : BottomSheetDialogFragment() {

    override fun getTheme(): Int {
        return R.style.DefaultBottomSheetDialog
    }

}