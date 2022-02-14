package com.clothex.user.customview

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.withStyledAttributes
import com.clothex.user.R
import com.clothex.user.utils.setRotationByLocale


/**
 * Created by Mohamed Elshafey on 20/12/2021.
 */
class DefaultActionBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.default_action_bar, this)
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        findViewById<ImageView>(R.id.backIV).setRotationByLocale()
        val metrics = context.resources.displayMetrics
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, metrics).let {
            setPadding(
                paddingLeft + it.toInt(),
                paddingTop,
                paddingRight + it.toInt(),
                paddingBottom
            )
        }
        context.withStyledAttributes(attrs, R.styleable.DefaultActionBar) {
            getString(R.styleable.DefaultActionBar_title)?.let { title -> setTitle(title) }
        }
    }

    fun setTitle(title: String) {
        findViewById<TextView>(R.id.titleTV).text = title
    }

}