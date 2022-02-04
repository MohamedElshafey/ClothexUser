package com.clothex.data.domain.model.order

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName
import io.clothex.data.R

enum class OrderState(
    val value: Int,
    @StringRes val title: Int,
    @ColorInt val contentColor: Int,
    @ColorInt val backgroundColor: Int
) {
    @SerializedName("pending")
    PENDING(0, R.string.pending, Color.parseColor("#974b00"), Color.parseColor("#ffedaf")),

    @SerializedName("booked")
    BOOKED(1, R.string.booked, Color.parseColor("#10c935"), Color.parseColor("#1A10c935")),

    @SerializedName("unbooked")
    UNBOOKED(2, R.string.unbooked, Color.parseColor("#646464"), Color.parseColor("#e9e9e9")),

    @SerializedName("rejected")
    REJECTED(3, R.string.rejected, Color.parseColor("#970000"), Color.parseColor("#ffdfdf")),

    @SerializedName("done")
    DONE(4, R.string.done, Color.parseColor("#646464"), Color.parseColor("#e9e9e9"))
}