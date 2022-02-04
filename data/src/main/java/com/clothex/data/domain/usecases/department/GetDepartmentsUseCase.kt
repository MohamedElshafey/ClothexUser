package com.clothex.data.domain.usecases.department

import com.clothex.data.domain.model.department.Department
import com.clothex.data.domain.repository.department.IGetDepartmentsRepository
import com.clothex.data.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohamed Elshafey on 11/17/2020.
 */

typealias GetDepartmentsBaseUseCase = BaseUseCase<Unit, Flow<Result<List<Department>>>>

class GetDepartmentsUseCase(private val repository: IGetDepartmentsRepository) :
    GetDepartmentsBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<Result<List<Department>>> = flow {
        try {
            repository.getDepartments().collect {
                emit(Result.success(it))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}