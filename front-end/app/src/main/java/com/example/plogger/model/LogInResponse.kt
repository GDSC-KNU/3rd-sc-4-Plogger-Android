package com.example.ecomate.model

import com.example.plogger.model.TokenInfo

data class LogInResponse(
    val message: String,
    val response: TokenInfo
)