package com.example.plogger.ui.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.plogger.ApplicationClass.Companion.sharedPreferencesUtil
import com.example.plogger.R
import com.example.plogger.databinding.ActivityLoginBinding
import com.example.plogger.ui.MainActivity
import com.example.plogger.ui.util.Util.hideKeyboard
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
//    private val logInViewModel: LogInViewModel by viewModels()
    private val googleSignInClient: GoogleSignInClient by lazy { getGoogleClient() }
    private val googleAuthLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

        try {
            val account = task.getResult(ApiException::class.java)

            val userName = account.displayName
            val userEmail = account.email
            val authCode = account.serverAuthCode

            sharedPreferencesUtil.setUserName(userName.toString())
            sharedPreferencesUtil.setUserEmail(userEmail.toString())
            sharedPreferencesUtil.setAuthCode(authCode.toString())

            startActivity(Intent(this.baseContext, MainActivity::class.java))
            finish()

        } catch (e: ApiException) {
            Log.e("GoogleLoginError", e.stackTraceToString())
        }
    }
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
            googleLoginBtn.setOnClickListener {
                requestGoogleLogin()
            }
        }
    }

    private fun requestGoogleLogin() {
        googleSignInClient.signOut()
        val signInIntent = googleSignInClient.signInIntent
        googleAuthLauncher.launch(signInIntent)
    }

    private fun getGoogleClient(): GoogleSignInClient {
        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestScopes(Scope("https://www.googleapis.com/auth/pubsub"))
            .requestServerAuthCode(getString(R.string.google_login_web_client_id))
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(this, googleSignInOption)
    }
}