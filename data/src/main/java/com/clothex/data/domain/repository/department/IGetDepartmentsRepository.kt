package com.clothex.data.domain.repository.department

import com.clothex.data.domain.model.department.Department
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface IGetDepartmentsRepository {
    suspend fun getDepartments(): Flow<List<Department>>
}