package com.example.plogger.model

import java.io.Serializable

data class Board(
    val boardId: Int,
    val memberId: Int,
    val nickname: String,
    val profileImage: String,
    val boardTitle: String,
    val boardContent: String,
    val boardImage: String,
    val likeCnt: Int,
    var liked: Boolean,
    val createdDate : String,
) : Serializable
