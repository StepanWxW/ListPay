package com.stepan.listpay.data.model

import com.google.gson.annotations.SerializedName


class ResponseTokenDTO (
    val success: Boolean,
    val response: TokenDTO,
    val error: ErrorDTO
        )