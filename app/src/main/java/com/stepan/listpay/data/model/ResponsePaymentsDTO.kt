package com.stepan.listpay.data.model

import com.stepan.listpay.domain.model.Payment

class ResponsePaymentsDTO (
    val success: Boolean,
    val response: List<Payment>,
    val error: ErrorDTO
)