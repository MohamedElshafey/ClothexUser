package com.clothex.user.home.home

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.data.domain.model.home.HomeShop
import com.clothex.data.domain.usecases.database.GetSavedLocationUseCase
import com.clothex.data.domain.usecases.home.GetHomeUseCase
import com.clothex.data.domain.usecases.local.GetIsFirstTimeOpenUseCase
import com.clothex.data.domain.usecases.sign.UpdateFCMTokenUseCase
import com.clothex.data.local.room.entity.SavedLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val homeUseCase: GetHomeUseCase,
    private val updateFCMTokenUseCase: UpdateFCMTokenUseCase,
    private val getIsFirstTimeOpenUseCase: GetIsFirstTimeOpenUseCase,
    private val getSavedLocationUseCase: GetSavedLocationUseCase
) : ViewModel() {

    val notificationCount = ObservableField("5")
    val productLiveData = MutableLiveData<List<HomeProduct>>()
    val shopLiveData = MutableLiveData<List<HomeShop>>()
    val failureLiveData = MutableLiveData<String>()
    val savedLocationLiveData = MutableLiveData<SavedLocation?>()
    var isFirstTimeOpenLiveData = MutableLiveData<Boolean>()

    fun checkFirstTimeOpen() {
        isFirstTimeOpenLiveData = MutableLiveData<Boolean>()
        viewModelScope.launch {
            getIsFirstTimeOpenUseCase.invoke(Unit).collect {
                isFirstTimeOpenLiveData.postValue(it)
            }
        }
    }

    fun fetchHome() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                homeUseCase.invoke(Unit).collect { homeResult ->
                    homeResult.getOrNull()?.let {
                        productLiveData.postValue(it.products)
                        shopLiveData.postValue(it.shops)
                    }
                    homeResult.exceptionOrNull()?.let {
                        failureLiveData.postValue(it.message)
                    }
                }
            }
        }
    }

    fun updateFCMToken(token: String) {
        viewModelScope.launch {
            updateFCMTokenUseCase(token).collect {
                Log.d("UPDATE_FCM", "inside splashViewModel updateFCMToken: ")
            }
        }
    }

    fun getSelectedLocation() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getSavedLocationUseCase.invoke(true).collect {
                    savedLocationLiveData.postValue(it)
                }
            }
        }
    }
}