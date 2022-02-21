package com.clothex.data.domain.model.shop

import android.os.Parcelable
import com.clothex.data.domain.model.SocialMedia
import com.clothex.data.domain.model.product.Branch
import com.clothex.data.domain.model.product.Media
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Shop(
    @SerializedName("_id")
    val id: String,
    val logo: Media?,
    private val name: String,
    @SerializedName("name_ar")
    private val nameAr: String?,
    val about: String?,
    @SerializedName("social_medias")
    val socialMedias: List<SocialMedia>,
    val branches: List<Branch>?,
    @SerializedName("has_book")
    val hasBook: Boolean
) : Parcelable {
    fun getName(isArabic: Boolean): String {
        return if (isArabic) nameAr ?: name
        else name
    }
}
