package com.stepan.listpay.domain.model

data class PaymentItem(
    val id: Int,
    val title: String,
    val amount: Amount?,
    val created: Long
)