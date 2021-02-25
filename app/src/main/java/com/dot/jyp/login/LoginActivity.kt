package com.dot.jyp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dot.jyp.lobby.LobbyActivity
import com.dot.jyp.databinding.ActivityLoginBinding
import com.dot.jyp.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 임시 로그인 : login -> lobby
        binding.btnLogin.setOnClickListener {
            val lobbyIntent = Intent(this, LobbyActivity::class.java)
            startActivity(lobbyIntent)
        }

        //--옵션 기능--------------------------------------------------------------------------------
        // 자동 로그인
        binding.llLoginAuto.setOnClickListener {

        }



        // 회원가입 : login -> register
        binding.btnLoginRegister.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }
    }
}