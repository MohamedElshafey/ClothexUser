package com.clothex.user.offer.details

import androidx.databinding.ObservableField
import com.clothex.data.domain.model.offer.Offer
import com.clothex.user.base.BaseLanguageViewModel

/**
 * Created by Mohamed Elshafey on 21/12/2021.
 */
class OfferDetailsViewModel(val offer: Offer) : BaseLanguageViewModel() {
    val title = ObservableField(offer.getTitle(isArabic()))
    val description = ObservableField(offer.getDescription(isArabic()))
    val cover = ObservableField(offer.cover.source)
}