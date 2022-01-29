package com.clothex.data.domain.repository.voucher

import com.clothex.data.domain.model.voucher.Voucher
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface IGetVouchersRepository {
    suspend fun getVouchers(): Flow<List<Voucher>>
}