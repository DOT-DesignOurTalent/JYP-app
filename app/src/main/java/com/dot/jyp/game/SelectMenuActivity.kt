package com.dot.jyp.game

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.dot.jyp.R
import com.dot.jyp.databinding.ActivitySelectMenuBinding
import com.dot.jyp.model.KakaoSearchResult
import com.dot.jyp.server.RetrofitSingleTon.kakaoService
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class SelectMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectMenuBinding
    private val TAG = "SelectMenuActivity"
    private var x  = 0.0
    private var y  = 0.0
    private var selectNum : Int = 1
    private lateinit var items : ArrayList<RestaurantInformation>
    private lateinit var selectedItem : ArrayList<String>

    private val nameToicon: Map<String, Int> = object : HashMap<String, Int>() {
        init {
            put("한식", R.drawable.ic_korean)
            put("양식", R.drawable.ic_fastfood)
            put("중식", R.drawable.ic_chinese)
            put("일식", R.drawable.ic_japanese)
            put("아시안 음식", R.drawable.ic_asian)
            put("치킨", R.drawable.ic_western)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSelectMenuChoice.setOnClickListener {
            //getLocation()
            //showRestaurantsList()
            selectedItem = ArrayList<String>()
            showDialog()
        }
        binding.btnSelectMenuNextInactive.setOnClickListener {
            Toast.makeText(this,"먼저 카테고리를 선택해주세요!",Toast.LENGTH_SHORT).show()
        }
        binding.btnSelectMenuNextActive.setOnClickListener {
            Toast.makeText(this,"입장중...",Toast.LENGTH_SHORT).show()
        }
        getIntentExtra()
    }

    fun getIntentExtra(){
        //이 부분은 intent로 가져와야할 부분!
        val nickname = "\"잘먹는 스컹크\""
        val isLogin = true
        if(isLogin)
            selectNum = 2
        binding.textSelectMenuComment1.text = nickname + getString(R.string.select_menu_state_before)
        binding.textSelectMenuComment2.text = nickname + getString(R.string.select_menu_state_after)
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
            val snackBar = Snackbar.make(
                view,
                "위치 권한이 필요합니다. 확인을 누르시면 설정화면으로 이동합니다.",
                Snackbar.LENGTH_LONG
            )
            snackBar.setAction("확인") {
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }.show()
        }

        //권한이 있는 경우
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        when{
            isNetworkEnabled -> {
                val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                if(location != null){
                    x = location.longitude
                    y = location.latitude
                }
            }
            isGpsEnabled -> {
                val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
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
        val call = kakaoService.getRestaurants("FD6", "126.926843", "37.562707", 500)
        call.enqueue(object : Callback<KakaoSearchResult> {
            override fun onResponse(
                call: Call<KakaoSearchResult>,
                response: Response<KakaoSearchResult>
            ) {
                val list = response.body()!!.documents
                items = ArrayList<RestaurantInformation>()
                for (document in list) {
                    items.add(RestaurantInformation(document.category_name.split(" > ")[1], document.place_name))
                    Log.e(TAG, "[카테고리 : ${document.category_name.split(" > ")[1]}] [식당이름 : ${document.place_name}]")
                }
                showDialog()
            }

            override fun onFailure(call: Call<KakaoSearchResult>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }

    //------ 다이얼로그 설정
    fun showDialog(){
        val inflater = LayoutInflater.from(this)
        val view: View = inflater.inflate(R.layout.dialog_select_menu, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val categoryName = arrayOf("한식", "양식", "중식", "일식", "아시안 음식", "치킨")
        val categorys =  ArrayList<LinearLayout>()
        categorys.add(view.findViewById(R.id.layout_dialog_select_category1))
        categorys.add(view.findViewById(R.id.layout_dialog_select_category2))
        categorys.add(view.findViewById(R.id.layout_dialog_select_category3))
        categorys.add(view.findViewById(R.id.layout_dialog_select_category4))
        categorys.add(view.findViewById(R.id.layout_dialog_select_category5))
        categorys.add(view.findViewById(R.id.layout_dialog_select_category6))

        //------ layout 클릭 이벤트 추가
        for((idx,category) in categorys.withIndex()){
            category.setOnClickListener {
                setSelectedItem(categoryName[idx], category)
            }
        }

        //------ 닫기버튼 클릭 이벤트 추가
        view.findViewById<ImageView>(R.id.image_select_dialog_close).setOnClickListener {
            dialog.dismiss()
        }

        //------ 신청완료 버튼 클릭 이벤트 추가
        view.findViewById<Button>(R.id.btn_dialog_select_finish).setOnClickListener {
            if(selectedItem.size != selectNum)
                Toast.makeText(this,"$selectNum 개를 선택해주세요!",Toast.LENGTH_SHORT).show()
            else {
                changeLayout()
                dialog.dismiss()
            }
        }
    }

    //------ 아이템이 이미 선정되었는지 체크하는 함수
    fun isSelected(name : String) : Boolean{
        for(category in selectedItem) {
            if (category == name)
                return true
        }
        return false
    }

    //------ selectedItem 클릭 이벤트 처리하는 함수
    fun setSelectedItem(name : String, category : LinearLayout){
        if(isSelected(name)) {
            //리스트에서 제거
            selectedItem.remove(name)
            category.alpha = 1f
        }
        else{
            if(selectedItem.size < selectNum){
                //리스트에 추가
                selectedItem.add(name)
                category.alpha = 0.5f
            }
            else
                Toast.makeText(this,"$selectNum 개 까지 선택하실 수 있습니다.",Toast.LENGTH_SHORT).show()
        }
    }

    //------ 레이아웃 변경하는 함수
    fun changeLayout(){
        binding.layoutSelectMenuBefore.visibility = View.GONE
        binding.layoutSelectMenuAfter.visibility = View.VISIBLE

        //------ 공통 선택 카테고리 설정
        var name : String = selectedItem.get(0)
        var resId : Int = nameToicon.get(name)!!
        binding.imageSelectMenuCategory1.setImageResource(resId)
        binding.textSelectMenuCategory1.text = name

        //------ 비회원 로그인
        if(selectNum == 1){
            binding.imageSelectMenuCategory2.visibility = View.GONE
            binding.textSelectMenuCategory2.visibility = View.GONE
        }
        //------ 회원 로그인
        else{
            name = selectedItem.get(1)
            resId  = nameToicon.get(name)!!
            binding.imageSelectMenuCategory2.setImageResource(resId)
            binding.textSelectMenuCategory2.text = name
            binding.imageSelectMenuCategory2.visibility = View.VISIBLE
            binding.textSelectMenuCategory2.visibility = View.VISIBLE
        }

        //------ 버튼 변경
        binding.btnSelectMenuChoice.text = "다시 정하기"
        binding.btnSelectMenuNextInactive.visibility = View.GONE
        binding.btnSelectMenuNextActive.visibility = View.VISIBLE
    }
}