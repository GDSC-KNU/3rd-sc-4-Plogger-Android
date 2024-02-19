package com.example.plogger.common

import android.content.Context
import android.content.SharedPreferences
import com.example.plogger.ApplicationClass.Companion.ACCESS_TOKEN
import com.example.plogger.ApplicationClass.Companion.AUTH_CODE
import com.example.plogger.ApplicationClass.Companion.MEMBER_ID
import com.example.plogger.ApplicationClass.Companion.REFRESH_TOKEN
import com.example.plogger.ApplicationClass.Companion.USER_EMAIL
import com.example.plogger.ApplicationClass.Companion.USER_NAME
import com.example.plogger.model.UserInfo

class SharedPreferences(context: Context) {
    private var preferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun setMemberId(memberId: Int) {
        val editor = preferences.edit()
        editor.putInt(MEMBER_ID, memberId)
        editor.apply()
    }

    fun getMemberId(): Int {
        return preferences.getInt(MEMBER_ID, 0)
    }

    fun setAccessToken(accessToken: String) {
        val editor = preferences.edit()
        editor.putString(ACCESS_TOKEN, accessToken)
        editor.apply()
    }

    fun getAccessToken(): String {
        return preferences.getString(ACCESS_TOKEN, "").toString()
    }

    fun setRefreshToken(refreshToken: String) {
        val editor = preferences.edit()
        editor.putString(REFRESH_TOKEN, refreshToken)
        editor.apply()
    }

    fun getRefreshToken(): String {
        return preferences.getString(REFRESH_TOKEN, "").toString()
    }

    fun deleteToken() {
        val editor = preferences.edit()
        editor.remove(MEMBER_ID)
        editor.remove(ACCESS_TOKEN)
        editor.remove(REFRESH_TOKEN)
        editor.remove(USER_NAME)
        editor.apply()
    }

    fun setUserName(name: String) {
        val editor = preferences.edit()
        editor.putString(USER_NAME, name)
        editor.apply()
    }
    fun getUserName(): String {
        return preferences.getString(USER_NAME, "plogger").toString()
    }
    fun setUserEmail(email: String) {
        val editor = preferences.edit()
        editor.putString(USER_EMAIL, email)
        editor.apply()
    }
    fun getUserEmail(): String {
        return preferences.getString(USER_EMAIL, "").toString()
    }
    fun setAuthCode(authCode: String) {
        val editor = preferences.edit()
        editor.putString(AUTH_CODE, authCode)
        editor.apply()
    }
    fun getAuthCode(): String {
        return preferences.getString(AUTH_CODE, "").toString()
    }
    companion object {
        private const val SHARED_PREFERENCES_NAME = "ecoMate"
    }
}