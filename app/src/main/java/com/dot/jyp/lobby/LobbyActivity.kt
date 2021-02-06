package com.dot.jyp.lobby

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import com.dot.jyp.R

class LobbyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lobby)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        var btn_create = findViewById<Button>(R.id.btn_lobby_create)
        btn_create.setOnClickListener{
            val intent = Intent(this, LinkActivity::class.java)
            startActivity(intent)
        }
    }
}