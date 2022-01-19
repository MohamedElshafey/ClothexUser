package com.clothex.user.data

import android.os.Parcelable
import com.clothex.data.domain.model.product.Media
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
@kotlinx.parcelize.Parcelize
data class Shop(
    val logoUrl: String,
    val name: String,
    val addressName: String,
    val workingHour: String,
    val location: LatLng? = null,
    val photos: List<Media>
) : Parcelable