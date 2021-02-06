package com.dot.jyp.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.dot.jyp.R
import com.dot.jyp.databinding.ActivityLoginBinding
import com.dot.jyp.databinding.ActivityRegisterBinding
import com.dot.jyp.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 임시 회원가입 : register -> login
        binding.registerBtn.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }

        binding.registerLookTOSBtn.setOnClickListener {
            val tosDialog = LayoutInflater.from(this).inflate(R.layout.dialog_register_tos, null)
            val tosBuilder = AlertDialog.Builder(this).setView(tosDialog)
            tosBuilder.show()
        }
    }
}