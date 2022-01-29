package com.clothex.data.domain.model.voucher

import android.os.Parcelable
import com.clothex.data.domain.model.home.HomeShop
import com.clothex.data.domain.model.product.Branch
import kotlinx.parcelize.Parcelize

@Parcelize
data class VoucherShop(
    val shop: HomeShop,
    val branch: Branch
):Parcelable
