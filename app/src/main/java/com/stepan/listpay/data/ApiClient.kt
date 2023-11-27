package com.stepan.listpay.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://easypay.world/api-test/"
    private var token: String? = null
    private class TokenInterceptor(private val token: String?) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
            token?.let {
                requestBuilder.header("Authorization", "Bearer $it")
            }
            val request = requestBuilder.build()
            return chain.proceed(request)
        }
    }

    private val appKeyInterceptor = Interceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("app-key", "12345")
            .header("v", "1")
        val request = requestBuilder.build()
        chain.proceed(request)
    }

    fun setToken(newToken: String) {
        token = newToken
        recreateClient()
    }

    private var okHttpClient = createOkHttpClient()

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(token))
            .addInterceptor(appKeyInterceptor)
            .build()
    }

    private fun recreateClient() {
        okHttpClient = createOkHttpClient()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}