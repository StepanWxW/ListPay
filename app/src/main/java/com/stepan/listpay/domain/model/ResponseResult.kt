package com.stepan.listpay.domain.model

open class ResponseResult {
    data class TokenResponse(val token: String) : ResponseResult()
    data class PaymentsResponse(val listPaymentItems: List<PaymentItem>) : ResponseResult()
    data class ErrorResponse(val errorCode: Int?, val errorMsg: String?) : ResponseResult()
}