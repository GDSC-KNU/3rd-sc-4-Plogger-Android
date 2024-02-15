package com.example.plogger.model

import java.io.Serializable

data class Chat(
    val chatId: Int,
    val memberId: Int,
    val nickname: String,
    val profileImage: String,
    val content: String,
) : Serializable