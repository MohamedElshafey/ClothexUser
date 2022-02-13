package com.clothex.user.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.withStyledAttributes
import com.clothex.user.R

/**
 * You can find a reference here @see [link](https://www.figma.com/file/Iu1yBczVP66MOvDPndLkSf/Halan-Design-system?node-id=5274%3A15581).
 *
 * Custom view that will be used to show the messages in the system.
 *
 * @property setIcon OR attribute [R.styleable.BaseMessage_android_icon] to set the view's icon.
 * @property setType OR attribute [R.styleable.BaseMessage_baseMessageType] to set the view's type.
 * @property setTitle OR attribute [R.styleable.BaseMessage_title] to set the view's title.
 * @property setDescription OR attribute [R.styleable.BaseMessage_description] to set the view's description.
 * @property setSize OR attribute [R.styleable.BaseMessage_baseMessageSize] to set the view's size.
 *
 */
class Message(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    val messageIV: ImageView
    val titleTextView: TextView
    val descriptionTextView: TextView

    init {
        inflate(context, R.layout.message, this)
        messageIV = findViewById(R.id.messageIV)
        titleTextView = findViewById(R.id.messageTitleTV)
        descriptionTextView = findViewById(R.id.messageSubTitleTV)
        orientation = VERTICAL
        gravity = Gravity.CENTER
        context.withStyledAttributes(attrs, R.styleable.BaseMessage) {
            getDrawable(R.styleable.BaseMessage_android_icon)?.let { icon -> setIcon(icon) }
            getString(R.styleable.BaseMessage_title)?.let { title -> setTitle(title) }
            getString(R.styleable.BaseMessage_description)?.let { desc -> setDescription(desc) }
        }
    }

    fun setIcon(icon: Drawable?) {
        messageIV.setImageDrawable(icon)
        messageIV.visibility = if (icon == null) GONE else VISIBLE
    }

    fun getIcon(): Drawable? = messageIV.drawable

    fun setTitle(title: String?) {
        titleTextView.text = title
        titleTextView.visibility = if (title.isNullOrEmpty()) GONE else VISIBLE
    }

    fun getTitle(): String? = titleTextView.text?.toString()

    fun setDescription(description: String?) {
        descriptionTextView.text = description
        descriptionTextView.visibility = if (description.isNullOrEmpty()) GONE else VISIBLE
    }

    fun getDescription(): String? = descriptionTextView.text?.toString()
}