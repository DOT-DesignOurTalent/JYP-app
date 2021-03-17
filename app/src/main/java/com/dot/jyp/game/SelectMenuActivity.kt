package com.dot.jyp.game

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dot.jyp.R
import com.dot.jyp.databinding.ActivitySelectMenuBinding
import com.dot.jyp.model.HashMapSingleTon.nameToicon

class SelectMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectMenuBinding
    private var selectNum : Int = 1
    private var checkCreate : Boolean = false
    private var checkAllSelect : Boolean = false
    private var selectItem1 : String = ""
    private var selectItem2 : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //------ 인텐트 정보 초기화
        checkCreate = intent.getBooleanExtra("isCreate",false)
        selectNum = intent.getIntExtra("selectNum", 1)

        //------ 카테고리 정하기 버튼1 이벤트 추가
        binding.imageSelectMenuButton1.setOnClickListener {
            showSelectDialog(1)
        }

        //------ 카테고리 정하기 버튼2 이벤트 추가
        if(selectNum == 2)
        {
            binding.imageSelectMenuButton2.visibility = View.VISIBLE
            binding.imageSelectMenuButton2.setOnClickListener {
                showSelectDialog(2)
            }
        }

        //------ 다음버튼 이벤트 추가
        binding.btnSelectMenuNext.setOnClickListener {
            //------ 다음버튼 비활성화
            if(!checkAllSelect)
                Toast.makeText(this,"먼저 카테고리를 선택해주세요!",Toast.LENGTH_SHORT).show()
            //------ 다음버튼 활성화, 방생성 X
            else if(!checkCreate)
                showJoinDialog()
            //------ 다음버튼 활성화, 방생성 O
            else{
                //------ 법점 액티비티 실행 코드 추가필요
            }
        }

    }

    //다이얼로그 설정 함수
    fun showSelectDialog(button : Int){
        val inflater = LayoutInflater.from(this)
        val view: View = inflater.inflate(R.layout.dialog_select_menu, null)
        val dialog = Dialog(this)
        val items = ArrayList<CategoryName>()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyler_item_select_menu)
        val lm = LinearLayoutManager(this)
        lateinit var adapter : SelectMenuListAdapter

        //------ 카테코리 항목 초기화
        items.add(CategoryName("한식", "양식","중식"))
        items.add(CategoryName("일식", "분식","아시아음식"))
        items.add(CategoryName("멕시칸", "햄버거","피자"))
        items.add(CategoryName("치킨", "간식","상관없음"))

        //------ 이미 선택된 카테고리 클릭못하게 조정
        if(button == 1)
            adapter = SelectMenuListAdapter(items, selectItem2)
        else
            adapter = SelectMenuListAdapter(items, selectItem1)

        //------ 리사이클러뷰 설정 및 다이얼로그 show
        recyclerView.adapter = adapter
        recyclerView.layoutManager = lm
        dialog.setContentView(view)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        //------ 클릭 리스너 설정
        adapter.setItemClickListener(object : SelectMenuListAdapter.OnItemClickListener{
            override fun onItemClick(v: View, position: Int, name: String) {
                //------ 첫번째 음식설정 버튼인 경우
                if(button == 1) {
                    binding.imageSelectMenuButton1.setImageResource(nameToicon[name]!!)
                    selectItem1 = name
                }
                //------ 두번째 음식설정 버튼인 경우
                else {
                    binding.imageSelectMenuButton2.setImageResource(nameToicon[name]!!)
                    selectItem2 = name
                }

                //------ 모두 선택된 경우
                if(selectItem1 != "" && selectItem2 != ""){
                    binding.btnSelectMenuNext.setBackgroundResource(R.drawable.btn_blue_200)
                    binding.btnSelectMenuNext.setTextColor(ContextCompat.getColor(baseContext,R.color.colorWhite))
                    checkAllSelect = true
                }
                dialog.dismiss()
            }

        })
        //------ 닫기버튼 클릭 이벤트 추가
        view.findViewById<ImageView>(R.id.image_select_dialog_close).setOnClickListener {
            dialog.dismiss()
        }
    }

    //법정생성 다이얼로그 생성및 호출 함수
    fun showJoinDialog(){
        val inflater = LayoutInflater.from(this)
        val view: View = inflater.inflate(R.layout.dialog_enter, null)
        val dialog = Dialog(this)
        dialog.setContentView(view)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        //------ 닫기버튼 클릭 이벤트 추가
        view.findViewById<ImageView>(R.id.image_dialog_lobby_close).setOnClickListener {
            dialog.dismiss()
        }

        //------ 신청완료 버튼 클릭 이벤트 추가
        view.findViewById<Button>(R.id.btn_dialog_lobby_enter).setOnClickListener {
            //------ 법점 액티비티 실행 코드 추가필요
            dialog.dismiss()
        }
    }
}