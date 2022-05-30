package com.clothex.user.home.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.department.Department
import com.clothex.data.domain.usecases.department.GetDepartmentsUseCase
import com.clothex.user.base.BaseLanguageViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CategoriesViewModel(private val getDepartmentsUseCase: GetDepartmentsUseCase) :
    BaseLanguageViewModel() {

    val departmentListLiveData = MutableLiveData<Result<List<Department>>>()

    fun fetchDepartments() {
        viewModelScope.launch {
            getDepartmentsUseCase.invoke(Unit).collect {
                departmentListLiveData.postValue(it)
            }
        }
    }

}