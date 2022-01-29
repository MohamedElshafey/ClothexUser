package com.clothex.data.remote.repository.voucher

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.qr.RequestRedeem
import com.clothex.data.domain.repository.voucher.IRedeemVoucherRepository
import com.clothex.data.remote.api.VoucherApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class RedeemVoucherRepository(private val apiService: VoucherApiService) :
    IRedeemVoucherRepository {
    override suspend fun redeemVoucher(voucherId: String): Flow<RequestRedeem> = flow {
        emit(apiService.requestRedeemVoucher(voucherId))
    }
}