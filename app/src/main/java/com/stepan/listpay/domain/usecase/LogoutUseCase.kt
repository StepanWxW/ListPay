package com.stepan.listpay.domain.usecase

import com.stepan.listpay.data.ApiClient


class LogoutUseCase () {
    fun logout() {
        ApiClient.setToken("")
    }
}