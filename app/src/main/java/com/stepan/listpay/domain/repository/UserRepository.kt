package com.stepan.listpay.domain.repository

import com.stepan.listpay.domain.model.ResponseResult

interface UserRepository {
    suspend fun getToken(login: String, password: String): ResponseResult
}