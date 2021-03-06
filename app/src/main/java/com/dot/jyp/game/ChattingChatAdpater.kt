package com.dot.jyp.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dot.jyp.R

class ChattingChatAdpater(val chatList: ArrayList<Chat>): RecyclerView.Adapter<ChattingChatAdpater.ChatHolder>() {
    inner class ChatHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nickname = itemView?.findViewById<TextView>(R.id.text_item_chatting_nickname)
        val chat = itemView?.findViewById<TextView>(R.id.text_item_chatting_chat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_chatting_chat, parent, false)
        return ChatHolder(v)
    }
    override fun getItemCount(): Int = if(chatList == null) 0 else chatList.size
    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        holder.nickname.text = "멋쟁이 고라니 : "
        holder.chat.text = chatList[position].chatMessage
    }
}
//data class Chat(val nickname: String, val chatMessage: String)
data class Chat(val chatMessage: String)