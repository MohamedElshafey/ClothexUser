package com.clothex.user.utils


import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.InsetDrawable
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.Group
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.clothex.user.R
import com.clothex.user.customview.DividerItemDecorator
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlin.math.roundToInt


/**
 * Created by Mohamed Elshafey on 19/11/2021.
 */

fun View.setShapeColor(color: Int) {
    val drawable = background as GradientDrawable?
    drawable?.setColor(color)
    background = drawable
}

fun ChipGroup.addChip(
    layoutInflater: LayoutInflater,
    vararg text: String,
    @LayoutRes layoutRes: Int = R.layout.item_chip,
    isChecked: Boolean = false
) {
    text.forEach {
        val chip = layoutInflater.inflate(layoutRes, this, false) as Chip
        chip.id = ViewCompat.generateViewId()
        chip.tag = it
        chip.isChecked = isChecked
        chip.text = it
        addView(chip)
    }
}

fun Group.setAllOnClickListener(listener: View.OnClickListener?) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnClickListener(listener)
    }
}

fun RecyclerView.addDivider() {
    val attrs = intArrayOf(android.R.attr.listDivider)
    val a = context.obtainStyledAttributes(attrs)
    val divider = a.getDrawable(0)
    val inset = resources.getDimensionPixelSize(R.dimen.divider_margin)
    val insetDivider = InsetDrawable(divider, inset, 0, inset, 0)
    a.recycle()
    val itemDecoration = DividerItemDecorator(insetDivider)
    addItemDecoration(itemDecoration)
}

fun View.setHeightPercentage(percentage: Int) {
    val metrics: DisplayMetrics = Resources.getSystem().displayMetrics
    val screenHeight = metrics.heightPixels
    val newParam = layoutParams
    newParam.height = (screenHeight * (percentage / 100f)).roundToInt()
    layoutParams = newParam
}