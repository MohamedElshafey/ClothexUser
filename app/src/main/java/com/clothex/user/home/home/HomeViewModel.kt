package com.clothex.user.home.home

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.department.Department
import com.clothex.data.domain.model.home.HomeProduct
import com.clothex.data.domain.model.home.HomeShop
import com.clothex.data.domain.usecases.database.GetSavedLocationUseCase
import com.clothex.data.domain.usecases.department.GetDepartmentsUseCase
import com.clothex.data.domain.usecases.home.GetHomeUseCase
import com.clothex.data.domain.usecases.local.GetIsFirstTimeOpenUseCase
import com.clothex.data.domain.usecases.sign.UpdateFCMTokenUseCase
import com.clothex.data.local.room.entity.SavedLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val homeUseCase: GetHomeUseCase,
    private val updateFCMTokenUseCase: UpdateFCMTokenUseCase,
    private val getIsFirstTimeOpenUseCase: GetIsFirstTimeOpenUseCase,
    private val getSavedLocationUseCase: GetSavedLocationUseCase,
    private val getDepartmentsUseCase: GetDepartmentsUseCase
) : ViewModel() {

    val notificationCount = ObservableField<String>()
    val notificationVisible = ObservableField(false)
    val productLiveData = MutableLiveData<List<HomeProduct>>()
    val shopLiveData = MutableLiveData<List<HomeShop>>()
    val failureLiveData: Flow<String> = flow {

    }
    val savedLocationLiveData = MutableLiveData<SavedLocation?>()
    var isFirstTimeOpenLiveData = MutableLiveData<Boolean>()
    val departmentListLiveData = MutableLiveData<Result<List<Department>>>()

    fun checkFirstTimeOpen() {
        isFirstTimeOpenLiveData = MutableLiveData<Boolean>()
        viewModelScope.launch {
            getIsFirstTimeOpenUseCase.invoke(Unit).collect {
                isFirstTimeOpenLiveData.postValue(it)
            }
        }
    }

    val loadingLiveData = MutableLiveData<Boolean>()

    fun fetchHome(callback: (String) -> Unit) {
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getDepartmentsUseCase.invoke(Unit).collect {
                    departmentListLiveData.postValue(it)
                }
                homeUseCase.invoke(Unit).collect { homeResult ->
                    homeResult.getOrNull()?.let {
                        productLiveData.postValue(it.products)
                        shopLiveData.postValue(it.shops)
                        with(it.notificationCount) {
                            notificationCount.set(this.coerceAtMost(99).toString())
                            notificationVisible.set(this > 0)
                        }
                    }
                    homeResult.exceptionOrNull()?.let {
                        callback.invoke(it.message ?: "")
                    }
                    loadingLiveData.postValue(false)
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