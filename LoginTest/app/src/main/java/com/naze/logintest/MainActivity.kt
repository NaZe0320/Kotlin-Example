package com.naze.logintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.naze.logintest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        NaverIdLoginSDK.initialize(context = this, "R5gEhRiOXchm2GYkJLE0","1QWa1OlsSI","LoginTest")
        //네아로 객체 초기화
        setNaverLogin()

    }

    private fun setNaverLogin() {
        val oauthLoginCallback = object : OAuthLoginCallback {
            override fun onError(errorCode: Int, message: String) {
                Log.d("Login Test Error","$errorCode : $message")
            }

            override fun onFailure(httpStatus: Int, message: String) {
                Log.d("Login Test Failure","${NaverIdLoginSDK.getLastErrorCode()} : $message")
            }

            override fun onSuccess() {
                Log.d("Login Test Success","Success ${NaverIdLoginSDK.getAccessToken()}")
            }
        }

        binding.btnOAuthLoginImg.setOAuthLogin(oauthLoginCallback)
    }
}