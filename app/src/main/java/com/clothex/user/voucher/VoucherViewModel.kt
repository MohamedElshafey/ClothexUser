package com.clothex.user.voucher

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.voucher.Voucher
import com.clothex.data.domain.usecases.sign.GetIsLoginTemporaryUseCase
import com.clothex.data.domain.usecases.voucher.GetVouchersUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class VoucherViewModel(
    private val getVouchersUseCase: GetVouchersUseCase,
    private val getIsLoginTemporaryUseCase: GetIsLoginTemporaryUseCase
) : ViewModel() {

    val vouchersLiveData = MutableLiveData<List<Voucher>>()
    val isLoginTemporaryLiveData = MutableLiveData<Boolean>()

    fun checkIfLoginTemporary() {
        viewModelScope.launch {
            getIsLoginTemporaryUseCase.invoke(Unit).collect {
                isLoginTemporaryLiveData.postValue(it)
            }
        }
    }

    fun fetchVouchers() {
        viewModelScope.launch {
            getVouchersUseCase.invoke(Unit).collect {
                vouchersLiveData.postValue(it)
            }
        }
    }

}