package com.example.plogger.ui.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.plogger.ApplicationClass.Companion.sharedPreferencesUtil
import com.example.plogger.databinding.ActivityLoginBinding
import com.example.plogger.ui.MainActivity
import com.example.plogger.ui.util.Util.hideKeyboard

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
//    private val logInViewModel: LogInViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e("싸피",sharedPreferencesUtil.getAccessToken())
        if (sharedPreferencesUtil.getAccessToken() != "") {

            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
        setUi()
    }

    private fun setUi() {
//        logInViewModel.token.observe(this){
//            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//            finish()
//        }
        binding.apply {
            loginBtn.setOnClickListener {
//                logInViewModel.logIn(loginId.text.toString(), loginPassword.text.toString(), this@LoginActivity)
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
                hideKeyboard(this@LoginActivity)
            }
            loginSignUp.setOnClickListener {
                startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
            }
        }
    }
}