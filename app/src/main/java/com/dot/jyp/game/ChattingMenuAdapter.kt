package com.dot.jyp.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dot.jyp.R

class ChattingMenuAdapter(val menus: ArrayList<MenuSelected>):
    RecyclerView.Adapter<ChattingMenuAdapter.MenuViewHolder>() {
    inner class MenuViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val menu = itemView?.findViewById<ImageView>(R.id.img_item_menu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_chatting_menu, parent, false)
        return MenuViewHolder(v)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val resId = holder.itemView.context.resources.getIdentifier(
            "ic_"+menus[position].menu, "drawable", holder.itemView.context.packageName)
        holder.menu.setImageResource(resId)
        if(!menus[position].selected){
            holder.menu.drawable.alpha = 50
        }
    }

    override fun getItemCount(): Int = if(menus == null) 0 else menus.size
}



data class MenuSelected(val menu: String, val selected: Boolean)
