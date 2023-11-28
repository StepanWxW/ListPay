package com.stepan.listpay.data

import com.google.gson.GsonBuilder
import com.stepan.listpay.domain.model.Amount
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
                requestBuilder.header("token", it)
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

    private val gson = GsonBuilder()
        .registerTypeAdapter(Amount::class.java, AmountDeserializer())
        .create()

    private var retrofit: Retrofit? = null

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(token))
            .addInterceptor(appKeyInterceptor)
            .build()
    }

    private fun recreateClient() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(createOkHttpClient())
            .build()
    }

    val apiService: ApiService
        get() {
            if (retrofit == null) {
                recreateClient()
            }
            return retrofit!!.create(ApiService::class.java)
        }
}