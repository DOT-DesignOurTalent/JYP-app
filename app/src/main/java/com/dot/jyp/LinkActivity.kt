package com.dot.jyp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView

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