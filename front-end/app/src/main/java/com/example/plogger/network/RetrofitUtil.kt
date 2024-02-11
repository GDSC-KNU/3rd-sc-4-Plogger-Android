package com.example.plogger.network

import com.example.plogger.ApplicationClass

class RetrofitUtil {
    companion object {
        val logInApi: LogInApi = ApplicationClass.retrofit.create(LogInApi::class.java)
    }
}