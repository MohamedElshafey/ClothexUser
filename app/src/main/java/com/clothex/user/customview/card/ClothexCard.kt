package com.clothex.user.customview.card

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import com.clothex.user.R
import com.clothex.user.utils.getEnum
import com.clothex.user.utils.toDP


/**
 * Created by Mohamed Elshafey on 20/12/2021.
 */
class ClothexCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val headerTV: TextView
    private val titleTV: TextView
    private val iconIV: ImageView
    private val root: LinearLayout

    init {
        val styledAttribute = context.obtainStyledAttributes(attrs, R.styleable.ClothexCard)
        styledAttribute.getEnum(R.styleable.ClothexCard_cardSize, CardSize.NORMAL).let { cardSize ->
            inflate(context, cardSize.layout, this)
        }
        headerTV = findViewById(R.id.headerTV)
        titleTV = findViewById(R.id.titleTV)
        iconIV = findViewById(R.id.iconIV)
        root = findViewById(R.id.container)
        with(styledAttribute) {
            setHeader(getString(R.styleable.ClothexCard_headerText))
            getString(R.styleable.ClothexCard_title)?.let { title -> setTitle(title) }
            getDrawable(R.styleable.ClothexCard_icon)?.let { icon -> setIcon(icon) }
            changeBackground(getColor(R.styleable.ClothexCard_cardBackground, Color.WHITE))
            setHasStroke(getBoolean(R.styleable.ClothexCard_hasStroke, false))
            setCornerRadius(getEnum(R.styleable.ClothexCard_cardSize, CardSize.NORMAL).cornerRadius)
        }
        styledAttribute.recycle()
    }

    private fun setCornerRadius(cornerRadius: Int) {
        val width = cornerRadius.toFloat().toDP(resources)
        val background = root.background as GradientDrawable
        background.cornerRadius = width.toFloat()
        root.background = background
    }

    fun setHasStroke(hasStroke: Boolean) {
        val background = root.background as GradientDrawable
        val width = 1f.toDP(resources)
        background.setStroke(
            if (hasStroke) width else 0,
            ContextCompat.getColor(context, R.color.mercury)
        )
        root.background = background
    }

    fun changeBackground(color: Int) {
        val background = root.background as GradientDrawable
        background.setColor(color)
        root.background = background
    }


    fun setIcon(icon: Drawable) {
        iconIV.setImageDrawable(icon)
    }

    fun setTitle(title: String) {
        titleTV.text = title
    }

    private fun setHeader(header: String?) {
        headerTV.isGone = header.isNullOrEmpty()
        headerTV.text = header
    }
}