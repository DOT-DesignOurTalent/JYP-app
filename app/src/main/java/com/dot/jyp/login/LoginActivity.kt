package com.dot.jyp.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.dot.jyp.R
import com.dot.jyp.databinding.ActivityLoginBinding
import com.dot.jyp.lobby.LobbyActivity
import com.dot.jyp.model.UserAccount
import com.dot.jyp.register.RegisterActivity
import com.dot.jyp.server.RetrofitSingleTon.backEndService
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences
    private lateinit var editor : SharedPreferences.Editor
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = baseContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        editor = preferences.edit()
        //--로그인 기능------------------------------------------------------------------------------
        var isEmailChecked = false
        binding.edittextLoginEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                if (binding.edittextLoginEmail.text.toString() != "") {
                    if (binding.edittextLoginPwd.text.toString() != "") {
                        activateButton(binding.btnLogin)
                    }
                    isEmailChecked = true
                } else {
                    inactivateButton(binding.btnLogin)
                    Log.d("버튼", "확인")
                    isEmailChecked = false
                }
            }
        })

        binding.edittextLoginPwd.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (isEmailChecked && binding.edittextLoginPwd.text.toString() != "") {
                    activateButton(binding.btnLogin)
                } else {
                    inactivateButton(binding.btnLogin)
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        // 로그인 : login -> lobby
        binding.btnLogin.setOnClickListener {
            val lobbyIntent = Intent(this, LobbyActivity::class.java)
            val user = UserAccount(binding.edittextLoginEmail.text.toString(), binding.edittextLoginPwd.text.toString())
            var callPostLogIn = backEndService.logIn(user)
            callPostLogIn.enqueue(object : retrofit2.Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.e("로그인", response.toString())
                    if (response.code() == 200) {
                        Toast.makeText(applicationContext, "법점에 오신 것을 환영합니다!", Toast.LENGTH_SHORT).show()
                        //------ SharedPreference 등록
                        editor.putString("email", binding.edittextLoginEmail.text.toString())
                        editor.commit()
                        //------ 이전 액티비티 전체 종료
                        lobbyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(lobbyIntent)
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.e("로그인", "실패 : $t")
                }
            })
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

    fun inactivateButton(btn: Button) {
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