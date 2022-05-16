package com.clothex.data.remote.repository.voucher

import com.clothex.data.domain.model.BaseResponseModel
import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.repository.voucher.IAddVoucherRepository
import com.clothex.data.remote.api.VoucherApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class AddVoucherRepository(private val apiService: VoucherApiService) : IAddVoucherRepository {
    override suspend fun addVoucher(code: String): Flow<BaseResponseModel<Boolean>> =
        flow { emit(apiService.addVoucher(code)) }
}