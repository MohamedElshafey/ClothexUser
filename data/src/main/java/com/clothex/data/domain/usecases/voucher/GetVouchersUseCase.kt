package com.clothex.data.domain.usecases.voucher

import com.clothex.data.domain.model.voucher.Voucher
import com.clothex.data.domain.repository.voucher.IGetVouchersRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetVouchersBaseUseCase = BaseUseCase<Unit, Flow<Result<List<Voucher>>>>

class GetVouchersUseCase(private val repository: IGetVouchersRepository) : GetVouchersBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<Result<List<Voucher>>> = flow {
        try {
            repository.getVouchers().collect {
                emit(Result.success(it))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}