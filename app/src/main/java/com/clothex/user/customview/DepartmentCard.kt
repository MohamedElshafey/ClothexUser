package com.clothex.user.customview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.withStyledAttributes
import com.clothex.user.R


/**
 * Created by Mohamed Elshafey on 20/12/2021.
 */
class DepartmentCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val titleTV: TextView
    val iconIV: ImageView
    val root: LinearLayout

    init {
        inflate(context, R.layout.department_card, this)
        titleTV = findViewById(R.id.departmentTitleTV)
        iconIV = findViewById(R.id.iconIV)
        root = findViewById(R.id.container)
        context.withStyledAttributes(attrs, R.styleable.DepartmentCustomView) {
            getString(R.styleable.DepartmentCustomView_title)?.let { title -> setTitle(title) }
            getDrawable(R.styleable.DepartmentCustomView_icon)?.let { icon -> setIcon(icon) }
            getColor(R.styleable.DepartmentCustomView_cardBackground, Color.WHITE).let { icon ->
                changeBackground(icon)
            }
        }
    }

    fun changeBackground(color: Int) {
        root.backgroundTintList = ColorStateList.valueOf(color)
        root.backgroundTintMode = PorterDuff.Mode.SRC_IN
    }

    fun setIcon(icon: Drawable) {
        iconIV.setImageDrawable(icon)
    }

    fun setTitle(title: String) {
        titleTV.text = title
    }

}