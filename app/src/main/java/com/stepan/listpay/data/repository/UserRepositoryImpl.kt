package com.stepan.listpay.data.repository

import com.stepan.listpay.data.ApiClient.apiService
import com.stepan.listpay.data.model.LoginRequest
import com.stepan.listpay.domain.model.ResponseResult
import com.stepan.listpay.domain.repository.UserRepository

class UserRepositoryImpl: UserRepository {
    override suspend fun getToken(login: String, password: String): ResponseResult {
        val response = apiService.postAuthorization(LoginRequest(login, password))
        if (response.isSuccessful) {
            if(response.body()?.success == true) {
                val body = response.body()?.response
                if (body != null) {
                    return ResponseResult.TokenResponse(body.token)
                }
            } else {
                return ResponseResult.ErrorResponse(response.body()?.error?.errorCode, response.body()?.error?.errorMsg)
            }
        }
        return ResponseResult.ErrorResponse(-1, "Unknown error")
    }
}