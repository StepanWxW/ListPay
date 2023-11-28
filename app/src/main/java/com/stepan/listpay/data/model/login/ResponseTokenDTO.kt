package com.stepan.listpay.data.model.login

import com.stepan.listpay.data.model.ErrorDTO


class ResponseTokenDTO (
    val success: Boolean,
    val response: TokenDTO,
    val error: ErrorDTO
        )