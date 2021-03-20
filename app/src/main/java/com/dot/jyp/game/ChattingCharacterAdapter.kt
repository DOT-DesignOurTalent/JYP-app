package com.dot.jyp.game


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dot.jyp.R

class ChattingCharacterAdapter(val characters: ArrayList<Character>):
    RecyclerView.Adapter<ChattingCharacterAdapter.CharacterViewHolder>() {
    inner class CharacterViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val nickname = itemView?.findViewById<TextView>(R.id.text_item_character_name)
        val charImage = itemView?.findViewById<ImageView>(R.id.img_item_character)
        val message = itemView?.findViewById<TextView>(R.id.text_item_character_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_chatting_character, parent, false)
        return CharacterViewHolder(v)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.nickname.text = characters[position].nickname
        val resId = holder.itemView.context.resources.getIdentifier(
            "ic_character_"+characters[position].imageNum, "drawable", holder.itemView.context.packageName)
        holder.charImage.setImageResource(resId)
        holder.message.text = characters[position].message
    }

    override fun getItemCount(): Int = if(characters == null) 0 else characters.size
}
data class Character(val nickname: String, val imageNum: String, var message: String)