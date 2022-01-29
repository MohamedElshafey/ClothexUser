package com.clothex.data.domain.usecases.voucher

import com.clothex.data.domain.model.voucher.Voucher
import com.clothex.data.domain.repository.voucher.IGetVouchersRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetVouchersBaseUseCase = BaseUseCase<Unit, Flow<List<Voucher>>>

class GetVouchersUseCase(private val repository: IGetVouchersRepository) : GetVouchersBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<List<Voucher>> = repository.getVouchers()
}