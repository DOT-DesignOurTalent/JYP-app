package com.dot.jyp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.dot.jyp.lobby.LobbyActivity
import com.dot.jyp.login.StartActivity


class IntroActivity : AppCompatActivity() {
    private val SPLASH_TIME : Long = 2000 // 1 sec
    private lateinit var preferences: SharedPreferences
    private lateinit var editor : SharedPreferences.Editor
    private lateinit var email : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        preferences = baseContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        editor = preferences.edit()
        email = preferences.getString("email","").toString()

        Handler().postDelayed({
            //------ 로그인된 경우
            if(email != "")
                startActivity(Intent(this, LobbyActivity::class.java))
            else
                startActivity(Intent(this,StartActivity::class.java))
            finish()
        }, SPLASH_TIME)
    }
}