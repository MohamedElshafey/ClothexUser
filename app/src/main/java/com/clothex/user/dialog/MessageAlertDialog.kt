package com.clothex.user.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.view.isGone
import com.clothex.user.R

/**
 * Created by Mohamed Elshafey on 14/12/2021.
 */
object MessageAlertDialog {

    fun showAlertDialog(
        context: Context,
        title: String?,
        description: String? = null,
        positiveButtonText: String? = null,
        negativeButtonText: String? = null,
        @DrawableRes iconRes: Int = -1,
        positiveOnClickListener: ((View) -> Unit)? = null,
        negativeOnClickListener: ((View) -> Unit)? = null
    ) {
        val view =
            LayoutInflater.from(context).inflate(R.layout.alert_dialog_message, null, false)
        val alertDialog = AlertDialog.Builder(context).setView(view).create()
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = view.findViewById<TextView>(R.id.descriptionTextView)
        val positiveButton = view.findViewById<TextView>(R.id.positiveButton)
        val negativeButton = view.findViewById<TextView>(R.id.negativeButton)
        if (iconRes != -1)
            imageView.setImageResource(iconRes)
        imageView.isGone = iconRes == -1
        titleTextView.text = title
        titleTextView.isGone = title.isNullOrEmpty()
        descriptionTextView.text = description
        descriptionTextView.isGone = description.isNullOrEmpty()
        positiveButton.text = positiveButtonText
        positiveButton.isGone = positiveButtonText.isNullOrEmpty()
        negativeButton.text = negativeButtonText
        negativeButton.isGone = negativeButtonText.isNullOrEmpty()
        positiveButton.setOnClickListener {
            positiveOnClickListener?.invoke(it)
            alertDialog.dismiss()
        }
        negativeButton.setOnClickListener {
            negativeOnClickListener?.invoke(it)
            alertDialog.dismiss()
        }
        alertDialog.setOnShowListener {
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.setWidthPercent(90)
        }
        alertDialog.show()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.setWidthPercent(90)
    }


    private fun Dialog.setWidthPercent(percentage: Int) {
        val percent = percentage.toFloat() / 100
        val dm = Resources.getSystem().displayMetrics
        val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWidth = rect.width() * percent
        window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }

}