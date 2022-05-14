package com.clothex.data.domain.model

/**
 * Created by Mohamed Elshafey on 11/24/2020.
 */
data class BaseResponseModel<T>(val status: Int?, val message: String?, val data: T?)