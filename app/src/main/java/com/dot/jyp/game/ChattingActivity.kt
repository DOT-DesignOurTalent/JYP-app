package com.dot.jyp.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dot.jyp.R
import com.dot.jyp.databinding.ActivityChattingBinding

class ChattingActivity : AppCompatActivity() {

    var characters = ArrayList<Character>()
    var characterAdapter = ChattingCharacterAdapter(characters)

    private lateinit var binding: ActivityChattingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var me: Int = 0 // 현재 나의 인덱스
        //--캐릭터 RecyclerView----------------------------------------------------------------------
        characters.add(Character("춤추는 고라니", "1", ""))
        characters.add(Character("난리난 고라니", "1", ""))
        characters.add(Character("배고픈 고라니", "1", ""))
        characters.add(Character("지친 고라니", "1", ""))
        characters.add(Character("재밌는 고라니", "1", ""))
        binding.recyclerChattingCharacters.adapter = characterAdapter
        binding.recyclerChattingCharacters.layoutManager = GridLayoutManager(this, 3)


        //--메뉴 RecyclerView------------------------------------------------------------------------
        val menus = arrayListOf<MenuSelected>(
            MenuSelected("korean", true), MenuSelected("japanese", false),
            MenuSelected("chinese", false), MenuSelected("western", false),
            MenuSelected("fastfood", false), MenuSelected("asian", false),
            MenuSelected("chinese", false), MenuSelected("fastfood", false),
            MenuSelected("fastfood", false), MenuSelected("dessert", false),
            MenuSelected("dessert", false)
        )
        val menuAdapter = ChattingMenuAdapter(menus)
        binding.recyclerChattingMenu.adapter = menuAdapter
        binding.recyclerChattingMenu.layoutManager = GridLayoutManager(this, 5)


        // 각 메세지 버튼 액션
        binding.btnChattingMessage1.setOnClickListener {
            sendMessage(binding.btnChattingMessage1.text.toString(), me)
        }
        binding.btnChattingMessage2.setOnClickListener {
            sendMessage(binding.btnChattingMessage2.text.toString(), me)
        }
        binding.btnChattingMessage3.setOnClickListener {
            sendMessage(binding.btnChattingMessage3.text.toString(), me)
        }
        binding.btnChattingMessage4.setOnClickListener {
            sendMessage(binding.btnChattingMessage4.text.toString(), me)
        }
        binding.btnChattingMessage5.setOnClickListener {
            sendMessage(binding.btnChattingMessage5.text.toString(), me)
        }


    }

    // 메세지 보내기 메소드
    fun sendMessage(text: String, me: Int){
        characters[me].message = text
        characterAdapter.notifyDataSetChanged()
    }
}