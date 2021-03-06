package com.dot.jyp.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dot.jyp.R
import com.dot.jyp.databinding.ActivityChattingBinding

class ChattingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChattingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)

        binding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //--채팅 RecyclerView-----------------------------------------------------------------------
        val chats = ArrayList<Chat>()
        val chatAdpater = ChattingChatAdpater(chats)
        binding.recyclerChattingChats.adapter = chatAdpater
        binding.recyclerChattingChats.layoutManager = LinearLayoutManager(this)


        //--메세지 RecyclerView----------------------------------------------------------------------
        // 보낼 메세지 String 리스트
        var messages: ArrayList<String> = arrayListOf(
            "안녕하세요~", "뭐 먹을까요?", "배고파요!", "식사합니다!", "빨리 고릅시다~", "시작해요!" )

        // 메세지 RecyclerView Adapter
        val msgAdapter = ChattingMsgBtnAdapter(applicationContext, messages)
        msgAdapter.setMessageClickListener(object: ChattingMsgBtnAdapter.MessageClickListener{
            override fun onMessageClick(view: View, position: Int) {
                Toast.makeText(applicationContext, messages[position], Toast.LENGTH_SHORT).show()
                chats.add(Chat(messages[position])) // 추후 닉네임 수정
                chatAdpater.notifyDataSetChanged()
                Log.d("채팅 크기", chats.size.toString())
            }
        })
        binding.recyclerChattingMsgBtn.adapter = msgAdapter
        binding.recyclerChattingMsgBtn.layoutManager = LinearLayoutManager(this).also{
            it.orientation = LinearLayoutManager.HORIZONTAL // 가로 RecyclerView
        }
        
    }
}