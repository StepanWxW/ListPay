package com.stepan.listpay.data.repository

import com.stepan.listpay.data.ApiClient
import com.stepan.listpay.domain.model.ResponseResult
import com.stepan.listpay.domain.repository.PaymentRepository

class PaymentRepositoryImpl: PaymentRepository {
    override suspend fun getPayments(): ResponseResult {
        val response = ApiClient.apiService.getPayments()
        if (response.isSuccessful) {
            if(response.body()?.success == true) {
                val body = response.body()?.response
                if (body != null) {
                    return ResponseResult.PaymentsResponse(body)
                }
            } else {
                return ResponseResult.ErrorResponse(response.body()?.error?.errorCode, response.body()?.error?.errorMsg)
            }
        }
        return ResponseResult.ErrorResponse(-1, "Unknown error")
    }
}