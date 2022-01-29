package com.clothex.user.voucher.add_text

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.usecases.voucher.AddVoucherUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Mohamed Elshafey on 21/12/2021.
 */
class CodeVoucherViewModel(
    private val addVoucherUseCase: AddVoucherUseCase
) : ViewModel() {

    val responseLiveData = MutableLiveData<Result<SimpleResponse>>()

    var isSendVoucher = false


    fun addVoucher(code: String) {
        isSendVoucher = true
        viewModelScope.launch {
            addVoucherUseCase.invoke(code).collect {
                responseLiveData.postValue(it)
            }
        }
    }

}