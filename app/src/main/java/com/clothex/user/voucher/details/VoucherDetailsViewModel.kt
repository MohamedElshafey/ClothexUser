package com.clothex.user.voucher.details

import androidx.databinding.ObservableField
import com.clothex.data.domain.model.voucher.Voucher
import com.clothex.user.base.BaseLanguageViewModel

/**
 * Created by Mohamed Elshafey on 21/12/2021.
 */
class VoucherDetailsViewModel(val voucher: Voucher) : BaseLanguageViewModel() {
    val title = ObservableField(voucher.getTitle(isArabic()))
    val description = ObservableField(voucher.getDescription(isArabic()))
    val cover = ObservableField(voucher.cover.source)
}