package com.clothex.user.network

import com.clothex.data.local.room.dao.SavedLocationDao
import com.clothex.data.local.shared_pref.LocalDataSourceImpl
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.internal.http2.ConnectionShutdownException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class HeadersInterceptor(
    private val localDataSourceImpl: LocalDataSourceImpl,
    private val savedLocationDao: SavedLocationDao
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        val shouldAddAuthHeaders = request.header("isAuthorizable") != "false"
        requestBuilder.removeHeader("isAuthorizable")
        if (shouldAddAuthHeaders) {
            val savedLocation = savedLocationDao.get(true)
            if (savedLocation != null) {
                requestBuilder
                    .addHeader("latitude", savedLocation.latitude.toString())
                    .addHeader("longitude", savedLocation.longitude.toString())
            }
            requestBuilder.addHeader(
                "Authorization",
                "Bearer ${localDataSourceImpl.getTokenAsString()}"
            )
        }
        val newReq = requestBuilder.build()
        try {
            return chain.proceed(newReq)
        } catch (e: Exception) {
            var msg = ""
            when (e) {
                is SocketTimeoutException -> {
                    msg = "Timeout - Please check your internet connection"
                }
                is UnknownHostException -> {
                    msg = "Unable to make a connection. Please check your internet"
                }
                is ConnectionShutdownException -> {
                    msg = "Connection shutdown. Please check your internet"
                }
                is IOException -> {
                    msg = "Server is unreachable, please try again later."
                }
                is IllegalStateException -> {
                    msg = "${e.message}"
                }
                else -> {
                    msg = "${e.message}"
                }
            }
            return Response.Builder()
                .request(newReq)
                .protocol(Protocol.HTTP_1_1)
                .code(999)
                .message(msg)
                .body(ResponseBody.create(null, "{${e}}")).build()
        }
    }
}