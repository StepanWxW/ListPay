package com.stepan.listpay.data.model

import com.google.gson.annotations.SerializedName

class ErrorDTO (
    @SerializedName("error_code")
    val errorCode: Int,
    @SerializedName("error_msg")
    val errorMsg: String,
        )