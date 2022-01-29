package com.clothex.data.remote.repository.voucher

import com.clothex.data.domain.model.voucher.Voucher
import com.clothex.data.domain.repository.voucher.IGetVouchersRepository
import com.clothex.data.remote.api.VoucherApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class GetVouchersRepository(private val apiService: VoucherApiService) : IGetVouchersRepository {
    override suspend fun getVouchers(): Flow<List<Voucher>> =
        flow { emit(apiService.getVouchers()) }
}