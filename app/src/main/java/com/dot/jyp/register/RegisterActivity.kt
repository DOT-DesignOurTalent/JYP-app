package com.dot.jyp.register

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.dot.jyp.R
import com.dot.jyp.databinding.ActivityLoginBinding
import com.dot.jyp.databinding.ActivityRegisterBinding
import com.dot.jyp.login.LoginActivity
import com.dot.jyp.model.Account
import com.dot.jyp.model.SignUpResult
import com.dot.jyp.server.RetrofitSingleTon
import com.dot.jyp.server.RetrofitSingleTon.backEndService
import com.google.android.gms.common.api.Response
import java.util.regex.Pattern
import javax.security.auth.callback.Callback

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //--이메일-----------------------------------------------------------------------------------
        // 이메일 유효성 검사
        var isEmailChecked = false
        binding.edittextRegisterEmail.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun afterTextChanged(p0: Editable?) {
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(p0.toString()).matches()){
                    binding.textRegisterEmailWrong.text = "이메일 형식을 확인해주세요."
                    binding.textRegisterEmailWrong.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorError))
                    binding.edittextRegisterEmail.setBackgroundResource(R.drawable.edit_error)
                    inactivateButton(binding.btnRegisterEmailCheck)
                    inactivateButton(binding.btnRegister)
                    isEmailChecked = false
                }else{
                    binding.textRegisterEmailWrong.text = ""
                    binding.edittextRegisterEmail.setBackgroundResource(R.drawable.edit_activate)
                    activateButton(binding.btnRegisterEmailCheck)
                    isEmailChecked = false
                }
            }
        })

        // 이메일 중복 체크 버튼 액션
        binding.btnRegisterEmailCheck.setOnClickListener {
            binding.textRegisterEmailWrong.text = "사용 가능한 이메일입니다."
            binding.textRegisterEmailWrong.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorCheck))
            binding.edittextRegisterEmail.setBackgroundResource(R.drawable.edit_check)
            isEmailChecked = true
        }

        //--비밀번호---------------------------------------------------------------------------------
        // 비밀번호 유효성 검사
        binding.edittextRegisterPwd.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun afterTextChanged(p0: Editable?) {
                // 숫자, 문자, 특수문자 포함 8~20자리
                if(!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&]).{8,20}.\$", p0.toString())){
                    binding.edittextRegisterPwd.setBackgroundResource(R.drawable.edit_alert)
                    binding.textRegisterPwdWrong.text="숫자, 문자, 특수 문자 포함 8자리 이상인지 확인해주세요."
                    binding.textRegisterPwdWrong.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorAlert))
                }else{
                    binding.edittextRegisterPwd.setBackgroundResource(R.drawable.edit_activate)
                    binding.textRegisterPwdWrong.text=""
                    binding.textRegisterPwdWrong.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorError))
                }
            }
        })


        // 비밀번호 확인 검사
        binding.edittextRegisterPwd2.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun afterTextChanged(p0: Editable?) {
                if (binding.edittextRegisterPwd.text.toString().equals(binding.edittextRegisterPwd2.text.toString())){
                    binding.edittextRegisterPwd.setBackgroundResource(R.drawable.edit_check)
                    binding.edittextRegisterPwd2.setBackgroundResource(R.drawable.edit_check)
                    binding.textRegisterPwdWrong.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorCheck))
                    binding.textRegisterPwdWrong.text = "사용 가능한 비밀번호입니다."
                    if(isEmailChecked) activateButton(binding.btnRegister) else inactivateButton(binding.btnRegister)
                }else{
                    binding.edittextRegisterPwd.setBackgroundResource(R.drawable.edit_error)
                    binding.edittextRegisterPwd2.setBackgroundResource(R.drawable.edit_error)
                    binding.textRegisterPwdWrong.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorError))
                    binding.textRegisterPwdWrong.text = "비밀번호를 확인해주세요."
                    inactivateButton(binding.btnRegister)
                }
            }
        })


        // 임시 회원가입 : register -> login
        binding.btnRegister.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            val data = Account(binding.edittextRegisterEmail.text.toString(),binding.edittextRegisterPwd.text.toString())
            var callPostSignUp = backEndService.signUp(data)
            callPostSignUp.enqueue(object: retrofit2.Callback<Void> {
                override fun onResponse(
                    call: retrofit2.Call<Void>,
                    response: retrofit2.Response<Void>
                ) {
                    if(response.code() == 201) {
                        Toast.makeText(applicationContext, "회원가입을 축하합니다!", Toast.LENGTH_SHORT).show()
                        // 로그인 페이지로 화면전환
                        startActivity(loginIntent)
                    }
                }
                override fun onFailure(call: retrofit2.Call<Void>, t: Throwable) {
                    Log.e("회원가입", "실패 : $t")
                }
            })
        }
    }

    // 버튼 비활성화 메소드
    fun inactivateButton(btn: Button) {
        btn.setBackgroundResource(R.drawable.btn_black_200)
        btn.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorGrey2))
        btn.isEnabled = false
        btn.isClickable = false
    }
    // 버튼 활성화 메소드
    fun activateButton(btn: Button){
        btn.setBackgroundResource(R.drawable.btn_blue_200)
        btn.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorWhite))
        btn.isEnabled = true
        btn.isClickable = true
    }

}