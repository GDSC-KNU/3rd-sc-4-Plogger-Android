package com.example.plogger.model

import java.io.Serializable

data class UserInfo(
    val name: String,
    val email: String,
    val authCode: String,
) : Serializable
