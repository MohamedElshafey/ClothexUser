package com.clothex.user.voucher

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.voucher.Voucher
import com.clothex.data.domain.usecases.voucher.GetVouchersUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class VoucherViewModel(private val getVouchersUseCase: GetVouchersUseCase) : ViewModel() {

    val vouchersLiveData = MutableLiveData<List<Voucher>>()

    fun fetchVouchers() {
        viewModelScope.launch {
            getVouchersUseCase.invoke(Unit).collect {
                vouchersLiveData.postValue(it)
            }
        }
    }

}