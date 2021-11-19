package com.clothex.user.utils

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.ViewCompat
import com.clothex.user.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

/**
 * Created by Mohamed Elshafey on 19/11/2021.
 */

fun View.setShapeColor(color: Int) {
    val drawable = background as GradientDrawable?
    drawable?.setColor(color)
    background = drawable
}

fun ChipGroup.addChip(layoutInflater: LayoutInflater, text: String) {
    val chip = layoutInflater.inflate(R.layout.item_chip, this, false) as Chip
    chip.id = ViewCompat.generateViewId()
    chip.text = text
    addView(chip)
}