package com.clothex.user.utils

import android.view.View
import android.widget.AdapterView

open class OnItemSelectedListener(private var onClickListener: (position: Int) -> Unit) :
    AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        onClickListener(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}