package com.dot.jyp.game

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dot.jyp.R
import com.dot.jyp.model.KakaoSearchResult
import com.dot.jyp.server.RetrofitSingleTon.kakaoService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectMenuActivity : AppCompatActivity() {
    private val TAG = "SelectMenuActivity"
    private var x  = 0.0
    private var y  = 0.0
    private lateinit var items : ArrayList<RestaurantInformation>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_menu)
        findViewById<Button>(R.id.btn_select_menu_choice).setOnClickListener {
            getLocation()
            showRestaurantsList()
        }
    }

    fun getLocation(){
        //권한여부 체크
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ){
            //권한없으면 스낵바 호출 후 함수 종료
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

        //권한이 있는 경우
        val layoutManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = layoutManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled = layoutManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        when{
            isNetworkEnabled -> {
                val location = layoutManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                if(location != null){
                    x = location.longitude
                    y = location.latitude
                }
            }
            isGpsEnabled -> {
                val location = layoutManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if(location != null){
                    x = location.longitude
                    y = location.latitude
                }
            }
        }
    }

    fun showRestaurantsList(){
        if(y == 0.0 && x == 0.0) {
            Log.e(TAG, "GPS 정보 로딩 실패!")
            return
        }
        //val call = kakaoService.getRestaurants("FD6",x.toString(),y.toString(),500)
        val call = kakaoService.getRestaurants("FD6","126.886361","37.652672",1000)
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