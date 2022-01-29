package com.clothex.user.voucher.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.qr.RequestRedeem
import com.clothex.data.domain.usecases.voucher.RedeemVoucherUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Mohamed Elshafey on 21/12/2021.
 */
class RedeemVoucherViewModel(private val redeemVoucherUseCase: RedeemVoucherUseCase) : ViewModel() {

//    val responseLiveData = MutableLiveData<Result<RequestRedeem>>()

    fun redeem(voucherId: String, callback: (Result<RequestRedeem>) -> Unit) {
        viewModelScope.launch {
            redeemVoucherUseCase.invoke(voucherId).collect {
//                responseLiveData.postValue(it)
                callback.invoke(it)
            }
        }
    }

}