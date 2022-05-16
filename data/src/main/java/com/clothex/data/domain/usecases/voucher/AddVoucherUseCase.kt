package com.clothex.data.domain.usecases.voucher

import com.clothex.data.domain.model.BaseResponseModel
import com.clothex.data.domain.repository.voucher.IAddVoucherRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias AddVoucherBaseUseCase = BaseUseCase<String, Flow<Result<BaseResponseModel<Boolean>>>>

class AddVoucherUseCase(private val repository: IAddVoucherRepository) : AddVoucherBaseUseCase {
    override suspend fun invoke(params: String): Flow<Result<BaseResponseModel<Boolean>>> = flow {
        try {
            repository.addVoucher(code = params).collect {
                emit(Result.success(it))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}