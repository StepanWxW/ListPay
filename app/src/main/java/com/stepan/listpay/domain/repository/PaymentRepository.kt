package com.stepan.listpay.domain.repository

import com.stepan.listpay.domain.model.ResponseResult


interface PaymentRepository {
    suspend fun getPayments(): ResponseResult
}