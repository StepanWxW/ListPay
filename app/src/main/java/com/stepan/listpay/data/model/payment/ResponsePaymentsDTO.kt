package com.stepan.listpay.data.model.payment

import com.stepan.listpay.data.model.ErrorDTO
import com.stepan.listpay.domain.model.PaymentItem

class ResponsePaymentsDTO (
    val success: Boolean,
    val response: List<PaymentItem>,
    val error: ErrorDTO
)