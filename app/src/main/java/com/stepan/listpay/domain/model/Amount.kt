package com.stepan.listpay.domain.model

import java.math.BigDecimal

sealed class Amount {
    data class Value(val value: BigDecimal) : Amount()
    data class Empty(val value: String) : Amount()
}