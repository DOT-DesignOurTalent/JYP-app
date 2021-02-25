package com.dot.jyp.lobby

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.dot.jyp.R


class LobbyActivity : AppCompatActivity() {
    private val requiredPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    private val multiplePermissionsCode = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lobby)

        checkPermissions()
        var btn_create = findViewById<Button>(R.id.btn_lobby_create)
        btn_create.setOnClickListener{
            val intent = Intent(this, LinkActivity::class.java)
            startActivity(intent)
        }


    }

    //퍼미션 체크 및 권한 요청 함수
    private fun checkPermissions() {
        //거절되었거나 아직 수락하지 않은 권한(퍼미션)을 저장할 문자열 배열 리스트
        var requestPermissionList = ArrayList<String>()

        //필요한 퍼미션들을 하나씩 끄집어내서 현재 권한을 받았는지 체크
        for(permission in requiredPermissions){
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                //만약 권한이 없다면 rejectedPermissionList에 추가
                requestPermissionList.add(permission)
            }
        }

        //요청할 퍼미션이 있다면
        if(requestPermissionList.isNotEmpty()){
            //권한 요청!
            val array = arrayOfNulls<String>(requestPermissionList.size)
            ActivityCompat.requestPermissions(
                this,
                requestPermissionList.toArray(array),
                multiplePermissionsCode
            )
        }
    }
}