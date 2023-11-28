package com.stepan.listpay.data

import com.stepan.listpay.data.model.login.LoginRequest
import com.stepan.listpay.data.model.payment.ResponsePaymentsDTO
import com.stepan.listpay.data.model.login.ResponseTokenDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun postAuthorization(
        @Body request: LoginRequest
    ): Response<ResponseTokenDTO>

    @GET("payments")
    suspend fun getPayments(): Response<ResponsePaymentsDTO>
}