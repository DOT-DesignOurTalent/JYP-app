package com.dot.jyp.lobby

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import com.dot.jyp.R
import com.dot.jyp.game.SelectMenuActivity

class LinkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_link)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        var btn_enter = findViewById<Button>(R.id.btn_link_enter)
        btn_enter.setOnClickListener{
            val intent = Intent(this, SelectMenuActivity::class.java)
            startActivity(intent)
        }
    }
}