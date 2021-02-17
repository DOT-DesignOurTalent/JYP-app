package com.dot.jyp.game

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.dot.jyp.R
import com.dot.jyp.model.KakaoSearchResult
import com.dot.jyp.server.RetrofitSingleTon.kakaoService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SelectMenuActivity : AppCompatActivity() {
    private val TAG = "SelectMenuActivity"
    private lateinit var x : String
    private lateinit var y : String
    private val requiredPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_menu)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        findViewById<LinearLayout>(R.id.linear_select_menu_layout).setOnClickListener {
            if(checkPermissions()) {
                getRestaurants()
            }
        }
    }

    //퍼미션 체크 및 권한 요청 함수
    private fun checkPermissions() : Boolean {
        //거절되었거나 아직 수락하지 않은 권한(퍼미션)을 저장할 문자열 배열 리스트
        //필요한 퍼미션들을 하나씩 끄집어내서 현재 권한을 받았는지 체크
        for (permission in requiredPermissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                val view = findViewById<View>(R.id.constraint_select_menu_layout)
                val snackBar = Snackbar.make(view, "위치 권한이 필요합니다. 확인을 누르시면 설정화면으로 이동합니다.", Snackbar.LENGTH_LONG)
                snackBar.setAction("확인") {
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                }.show()
                return false;
            }
        }
        return true;
    }
    fun getRestaurants(){
        var fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        //권한이 없으면 함수 종료
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        )
            return
        //현재 위치값을 받아옴
        fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
            //성공적으로 받아온 경우
            if (location != null) {
                val y = location.latitude
                val x = location.longitude
                //위도, 경도값 출력

                val call = kakaoService.getRestaurants("FD6",x.toString(),y.toString(),500)
                call.enqueue(object : Callback<KakaoSearchResult> {
                    override fun onResponse(
                        call: Call<KakaoSearchResult>,
                        response: Response<KakaoSearchResult>
                    ) {
                        val list = response.body()!!.documents
                        for(document in list){
                            Log.e(TAG,"[카테고리 : ${document.category_name}] [식당이름 : ${document.place_name}]")
                        }
                    }

                    override fun onFailure(call: Call<KakaoSearchResult>, t: Throwable) {
                        Log.e(TAG,t.message.toString())
                    }
                })
            }
        }
//        val call = kakaoService.getRestaurants("FD6","126.886361","37.652672",1000)
//        call.enqueue(object : Callback<KakaoSearchResult> {
//            override fun onResponse(
//                call: Call<KakaoSearchResult>,
//                response: Response<KakaoSearchResult>
//            ) {
//                val list = response.body()!!.documents
//                for(document in list){
//                    Log.e(TAG,"카테고리 : ${document.category_name} 식당이름 : ${document.place_name}")
//                }
//            }
//
//            override fun onFailure(call: Call<KakaoSearchResult>, t: Throwable) {
//                Log.e(TAG,t.message.toString())
//            }
//        })
    }
    fun getrestaurant(){
        val call = kakaoService.getRestaurants("FD6",x,y,1000)
        call.enqueue(object : Callback<KakaoSearchResult> {
            override fun onResponse(
                call: Call<KakaoSearchResult>,
                response: Response<KakaoSearchResult>
            ) {
                Log.e(TAG,response.body().toString())
            }

            override fun onFailure(call: Call<KakaoSearchResult>, t: Throwable) {
                Log.e(TAG,t.message.toString())
            }
        })
    }
}