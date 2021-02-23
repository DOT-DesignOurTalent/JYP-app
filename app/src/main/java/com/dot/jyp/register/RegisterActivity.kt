package com.dot.jyp.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.dot.jyp.R
import com.dot.jyp.databinding.ActivityLoginBinding
import com.dot.jyp.databinding.ActivityRegisterBinding
import com.dot.jyp.login.LoginActivity
import com.dot.jyp.server.RetrofitSingleTon

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 임시 회원가입 : register -> login
        binding.btnRegister.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }


    }
}