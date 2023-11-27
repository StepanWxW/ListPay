package com.stepan.listpay.domain.usecase

import com.stepan.listpay.domain.model.ResponseResult
import com.stepan.listpay.domain.repository.PaymentRepository

class GetPaymentsUseCase(private val paymentRepository: PaymentRepository) {
    suspend fun getPayments(): ResponseResult {
        return paymentRepository.getPayments()
    }
}