package com.clothex.user.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
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
    val newUrl = url?.replace("localhost", BuildConfig.LOCALHOST_IP)
    val placeholder = R.drawable.onboarding_image_1
    if (newUrl != null)
        Glide.with(imageView).load(newUrl)
            .apply(
                RequestOptions()
                    .placeholder(placeholder)
                    .error(placeholder)
                    .dontTransform()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            )
            .into(imageView)
}