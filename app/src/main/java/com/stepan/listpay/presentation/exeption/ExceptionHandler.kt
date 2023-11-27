package com.stepan.listpay.presentation.exeption

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ExceptionHandler(private val context: Context) {
    fun handleException(e: Exception) {
        when (e) {
            is UnknownHostException -> {
                showToast("Отсутствует интернет")
            }
            is SocketTimeoutException -> {
                showToast("Сервер не отвечает")
            }
            else -> {
                Log.d("MyTag", e.toString())
                showToast("Произошла ошибка")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}