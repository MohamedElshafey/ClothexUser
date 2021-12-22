package com.clothex.user.voucher.details

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.clothex.user.data.Voucher

/**
 * Created by Mohamed Elshafey on 21/12/2021.
 */
class VoucherDetailsViewModel(voucher: Voucher) : ViewModel() {

    val title = ObservableField(voucher.title)
    val subtitle = ObservableField(voucher.subtitle)
    val terms = ObservableField(voucher.terms)

}