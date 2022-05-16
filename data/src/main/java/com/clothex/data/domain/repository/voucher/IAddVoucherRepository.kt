package com.clothex.data.domain.repository.voucher

import com.clothex.data.domain.model.BaseResponseModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface IAddVoucherRepository {
    suspend fun addVoucher(code: String): Flow<BaseResponseModel<Boolean>>
}