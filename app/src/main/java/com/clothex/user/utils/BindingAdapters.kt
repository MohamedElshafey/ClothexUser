package com.clothex.user.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.clothex.user.BuildConfig
import com.clothex.user.R

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */

@BindingAdapter("android:text")
fun setText(textView: TextView, text: String?) {
    textView.visibility = if (text.isNullOrEmpty()) View.GONE else View.VISIBLE
    textView.text = text
}

@BindingAdapter("imageUrl")
fun setImageFromUrl(imageView: ImageView, url: String?) {
    imageView.setImageDrawable(null)
    val newUrl = url?.replace("localhost:3000", BuildConfig.LOCALHOST_IP)
    if (newUrl != null)
        Glide.with(imageView.context)
            .load(newUrl)
            .placeholder(R.drawable.loading_gradient)
            .error(R.drawable.onboarding_image_1)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
}