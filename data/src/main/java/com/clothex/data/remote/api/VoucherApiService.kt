package com.clothex.data.remote.api

import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.qr.RequestRedeem
import com.clothex.data.domain.model.voucher.Voucher
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by Mohamed Elshafey on 10/10/2020.
 */
interface VoucherApiService {
    @GET("my_vouchers")
    suspend fun getVouchers(): List<Voucher>

    @POST("add_voucher")
    suspend fun addVoucher(@Query("code") code: String): SimpleResponse

    @POST("request_redeem_voucher")
    suspend fun requestRedeemVoucher(@Query("voucher_id") voucherId: String): RequestRedeem
}