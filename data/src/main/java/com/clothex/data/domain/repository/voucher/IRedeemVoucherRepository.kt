package com.clothex.data.domain.repository.voucher

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.qr.RequestRedeem
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface IRedeemVoucherRepository {
    suspend fun redeemVoucher(voucherId: String): Flow<RequestRedeem>
}