package com.dot.jyp.game

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.dot.jyp.R
import com.dot.jyp.databinding.ActivitySelectMenuBinding
import com.dot.jyp.model.HashMapSingleTon.nameToicon

class SelectMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectMenuBinding
    private var selectNum : Int = 1
    private lateinit var selectedItem : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //------ 뒤로가기 버튼 이벤트 추가
        binding.imageSelectMenuBack.setOnClickListener {
            finish()
        }

        //------ 카테고리 정하기 버튼 이벤트 추가
        binding.btnSelectMenuChoice.setOnClickListener {
            selectedItem = ArrayList<String>()
            showDialog()
        }

        //------ 비활성화된 다음버튼 이벤트 추가
        binding.btnSelectMenuNextInactive.setOnClickListener {
            Toast.makeText(this,"먼저 카테고리를 선택해주세요!",Toast.LENGTH_SHORT).show()
        }

        //------ 활성화된 다음버튼 이벤트 추가
        binding.btnSelectMenuNextActive.setOnClickListener {
            Toast.makeText(this,"입장중...",Toast.LENGTH_SHORT).show()
        }
        getIntentExtra()
    }

    fun getIntentExtra(){
        //------ 이 부분은 intent로 가져와야할 부분!
        val nickname = "\"잘먹는 스컹크\""
        val isLogin = true
        if(isLogin)
            selectNum = 2
        binding.textSelectMenuComment1.text = nickname + getString(R.string.select_menu_state_before)
        binding.textSelectMenuComment2.text = nickname + getString(R.string.select_menu_state_after)
    }

    //다이얼로그 설정 함수
    fun showDialog(){
        val inflater = LayoutInflater.from(this)
        val view: View = inflater.inflate(R.layout.dialog_select_menu, null)
//        val builder = AlertDialog.Builder(this)
//        builder.setView(view)
        val dialog = Dialog(this)
        dialog.setContentView(view)
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

    //아이템이 이미 선정되었는지 체크하는 함수
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

    //레이아웃 변경하는 함수
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