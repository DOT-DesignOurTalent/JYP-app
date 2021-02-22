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
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var items : ArrayList<RestaurantInformation>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_menu)
        findViewById<LinearLayout>(R.id.linear_select_menu_layout).setOnClickListener {
            getRestaurants()
        }
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
        ){
            val view = findViewById<View>(R.id.constraint_select_menu_layout)
            val snackBar = Snackbar.make(view, "위치 권한이 필요합니다. 확인을 누르시면 설정화면으로 이동합니다.", Snackbar.LENGTH_LONG)
            snackBar.setAction("확인") {
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }.show()
        }
        //현재 위치값을 받아옴
        fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
            //성공적으로 받아온 경우
            if (location != null) {
                val y = location.latitude
                val x = location.longitude
                //위도, 경도값 출력

                val call = kakaoService.getRestaurants("FD6",x.toString(),y.toString(),500)
                //val call = kakaoService.getRestaurants("FD6","126.886361","37.652672",1000)
                call.enqueue(object : Callback<KakaoSearchResult> {
                    override fun onResponse(
                        call: Call<KakaoSearchResult>,
                        response: Response<KakaoSearchResult>
                    ) {
                        val list = response.body()!!.documents
                        items = ArrayList<RestaurantInformation>()
                        for(document in list){
                            items.add(RestaurantInformation(document.category_name, document.place_name))
                            Log.e(TAG,"[카테고리 : ${document.category_name}] [식당이름 : ${document.place_name}]")
                        }
                        showDialog()
                    }

                    override fun onFailure(call: Call<KakaoSearchResult>, t: Throwable) {
                        Log.e(TAG,t.message.toString())
                    }
                })
            }
        }
    }
    fun showDialog(){

        val inflater = LayoutInflater.from(this)
        val view: View = inflater.inflate(R.layout.dialog_select_menu, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val dialog = builder.create()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_select_menu_dialog)
        val selectMenuListAdapter = SelectMenuListAdapter(items)
        val layoutManager = LinearLayoutManager(baseContext)
        recyclerView.adapter = selectMenuListAdapter
        recyclerView.layoutManager = layoutManager

        Log.e(TAG, "size : ${items.size}")
        selectMenuListAdapter.setItemClickListener(object : SelectMenuListAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                Toast.makeText(baseContext, items.get(position).restaurant,Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

        })

        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
    }
}