package com.dot.jyp.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import androidx.core.content.ContextCompat
import com.dot.jyp.R
import com.dot.jyp.lobby.LobbyActivity
import com.dot.jyp.databinding.ActivityLoginBinding
import com.dot.jyp.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //--로그인 기능------------------------------------------------------------------------------
        var isEmailChecked = false
        binding.edittextLoginEmail.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun afterTextChanged(p0: Editable?) {
                if(binding.edittextLoginEmail.text.toString() != ""){
                    if(binding.edittextLoginPwd.text.toString() != ""){
                        activateButton(binding.btnLogin)
                    }
                    isEmailChecked = true
                }else{
                    inactivateButton(binding.btnLogin)
                    Log.d("버튼", "확인")
                    isEmailChecked = false
                }
            }
        })

        binding.edittextLoginPwd.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(isEmailChecked && binding.edittextLoginPwd.text.toString() != ""){
                    activateButton(binding.btnLogin)
                }else{
                    inactivateButton(binding.btnLogin)
                }
            }
            override fun afterTextChanged(p0: Editable?) { }
        })

        // 임시 로그인 : login -> lobby
        binding.btnLogin.setOnClickListener {
            val lobbyIntent = Intent(this, LobbyActivity::class.java)
            startActivity(lobbyIntent)

        }


        //--옵션 기능--------------------------------------------------------------------------------
        // 자동 로그인
        var autoCheck :Boolean = false
        binding.llLoginAuto.setOnClickListener {
            if(autoCheck){
                autoCheck = false
                binding.imageLoginAuto.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.colorMainBlue))
            }else{
                autoCheck = true
                binding.imageLoginAuto.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.colorGrey2))
            }
        }



        // 회원가입 : login -> register
        binding.btnLoginRegister.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }
    }

    fun inactivateButton(btn :Button) {
        btn.setBackgroundResource(R.drawable.btn_black_200)
        btn.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorGrey2))
        btn.isEnabled = false
        btn.isClickable = false
    }

    fun activateButton(btn: Button) {
        btn.setBackgroundResource(R.drawable.btn_blue_200)
        btn.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorWhite))
        btn.isEnabled = true
        btn.isClickable = true
    }
}