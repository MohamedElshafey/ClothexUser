package com.clothex.user.voucher.details

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.clothex.data.domain.model.voucher.Voucher
import com.clothex.user.utils.DateUtil.toLocalTimeZone

/**
 * Created by Mohamed Elshafey on 21/12/2021.
 */
class VoucherDetailsViewModel(val voucher: Voucher) : ViewModel() {
    val title = ObservableField(voucher.title)
    val expiryDate = ObservableField("Expired in ${voucher.expiryDate.toLocalTimeZone()}")
    val description = ObservableField(voucher.description)
    val cover = ObservableField(voucher.cover.source)
}