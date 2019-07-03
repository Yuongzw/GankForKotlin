package com.yuong.library_base.exception

import android.util.Log

import com.google.gson.JsonParseException
import com.yuong.library_base.MyConstants

import java.net.ConnectException
import java.net.SocketTimeoutException

import retrofit2.HttpException

object ApiExceptionFactory {

    private val TAG = "ApiExceptionFactory"
    fun getApiException(e: Throwable): ApiException {
        Log.e(TAG, e.javaClass.name)
        val apiException = ApiException(e)
        val msg: String
        val code: Int
        if (e is ConnectException) {
            msg = "无法连接服务器！"
            code = MyConstants.NETWORD_ERROR
        } else if (e is JsonParseException) {
            msg = "服务器返回错误！"
            code = MyConstants.JSON_ERROR
        } else if (e is SocketTimeoutException) {
            msg = "连接服务器超时！\""
            code = MyConstants.CONNECT_ERROR
        } else if (e is HttpException) {
            msg = "404错误(网址不存在)！"
            code = MyConstants.ERROR404
        } else {
            msg = "未知错误！"
            code = MyConstants.UNKNOWN_ERROR
        }
        apiException.code = code
        apiException.displayMessage = msg
        return apiException
    }
}
