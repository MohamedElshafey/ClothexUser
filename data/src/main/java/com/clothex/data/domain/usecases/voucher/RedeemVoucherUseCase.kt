package com.clothex.data.domain.usecases.voucher

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.qr.RequestRedeem
import com.clothex.data.domain.repository.voucher.IRedeemVoucherRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias RedeemVoucherBaseUseCase = BaseUseCase<String, Flow<Result<RequestRedeem>>>

class RedeemVoucherUseCase(private val repository: IRedeemVoucherRepository) :
    RedeemVoucherBaseUseCase {
    override suspend fun invoke(params: String): Flow<Result<RequestRedeem>> = flow {
        try {
            repository.redeemVoucher(voucherId = params).collect {
                emit(Result.success(it))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}