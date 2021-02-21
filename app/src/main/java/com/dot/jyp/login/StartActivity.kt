package com.dot.jyp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dot.jyp.R
import com.dot.jyp.databinding.ActivityLoginBinding
import com.dot.jyp.databinding.ActivityStartBinding
import com.dot.jyp.lobby.LobbyActivity

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartMemberLogin.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }

        binding.btnStartNonmemLogin.setOnClickListener {
            val lobbyIntent = Intent(this, LobbyActivity::class.java)
            startActivity(lobbyIntent)
        }
    }
}