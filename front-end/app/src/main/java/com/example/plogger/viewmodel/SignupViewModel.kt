package com.example.plogger.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.SignUpBody
import com.example.plogger.network.RetrofitUtil
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {
    private val _memberId = MutableLiveData<Int>()
    val memberId: LiveData<Int>
        get() = _memberId

    fun signUp(
        name: String,
        password: String,
        nickname: String,
        email: String,
        statusMessage: String,
    ) {
        viewModelScope.launch {
            _memberId.value = RetrofitUtil.logInApi.signup(
                SignUpBody(name,password,nickname,email,statusMessage)
            ).response["memberId"]
        }
    }
}