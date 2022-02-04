package com.clothex.data.remote.api

import com.clothex.data.domain.model.department.Department
import retrofit2.http.GET

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface DepartmentApiService {
    @GET("departments")
    suspend fun getDepartments(): List<Department>
}