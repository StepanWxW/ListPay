package com.stepan.listpay.domain.usecase

import com.stepan.listpay.data.ApiClient
import com.stepan.listpay.domain.model.ResponseResult
import com.stepan.listpay.domain.repository.UserRepository

class GetTokenUseCase(private val userRepository: UserRepository) {
    suspend fun getToken(login: String, password: String): ResponseResult {
        val authResult = userRepository.getToken(login, password)
        if(authResult is ResponseResult.TokenResponse){
            val token = authResult.token
            ApiClient.setToken(token)
        }
        return authResult
    }
}