package com.dot.jyp.game

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.dot.jyp.R

class ChattingMsgBtnAdapter(val context: Context, private val messages: ArrayList<String>): RecyclerView.Adapter<ChattingMsgBtnAdapter.MsgViewHolder>() {

    // 메세지 클릭 인터페이스
    interface MessageClickListener{
        fun onMessageClick(view: View, position: Int)
    }
    private lateinit var messageClick: MessageClickListener
    fun setMessageClickListener(msgClick: MessageClickListener) {
        this.messageClick = msgClick
    }

    // 메세지 RecyclerView 연결
    inner class MsgViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val messageBtn = itemView.findViewById<Button>(R.id.btn_item_chatting_msg)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsgViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_chatting_msg, parent, false)
        return MsgViewHolder(v)
    }
    override fun onBindViewHolder(holder: MsgViewHolder, position: Int) {
        holder.messageBtn.text = messages[position]
        holder.messageBtn.setOnClickListener {
            messageClick.onMessageClick(it, position)
        }
    }
    override fun getItemCount(): Int = messages.size
}