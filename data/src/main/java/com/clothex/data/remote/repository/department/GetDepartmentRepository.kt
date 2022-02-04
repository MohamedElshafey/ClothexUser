package com.clothex.data.remote.repository.department

import com.clothex.data.domain.model.department.Department
import com.clothex.data.domain.repository.department.IGetDepartmentsRepository
import com.clothex.data.remote.api.DepartmentApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */
class GetDepartmentRepository(private val apiService: DepartmentApiService) :
    IGetDepartmentsRepository {

    override suspend fun getDepartments(): Flow<List<Department>> = flow {
        emit(apiService.getDepartments())
    }
}