package com.dot.jyp.game

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dot.jyp.R
import java.util.*

class SelectMenuListAdapter(val items: ArrayList<RestaurantInformation>) : RecyclerView.Adapter<SelectMenuListAdapter.ViewHolder>(){
    //카테고리 - 아이콘 매핑 Map
    private val textToicon: Map<String, Int> = object : HashMap<String, Int>() {
        init {
            put("한식", R.drawable.ic_korean)
            put("일식", R.drawable.ic_japanese)
            put("중식", R.drawable.ic_chinese)
            put("아시아음식", R.drawable.ic_asian)
            put("치킨", R.drawable.ic_western)
            put("간식", R.drawable.ic_dessert)
            put("양식", R.drawable.ic_fastfood)
        }
    }

    //ClickListener
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    private lateinit var itemClickListener : OnItemClickListener

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val category = itemView.findViewById<TextView>(R.id.text_item_select_menu_category)
        val restaurant = itemView.findViewById<TextView>(R.id.text_item_select_menu_restaurants)
        val layout = itemView.findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.constraint_item_select_menu_layout)
        val icon = itemView.findViewById<ImageView>(R.id.image_item_select_menu_icon)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectMenuListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_select_menu, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: SelectMenuListAdapter.ViewHolder, position: Int) {
        val item = items[position]

        holder.category.text = item.category
        holder.restaurant.text = item.restaurant
        holder.layout.setOnClickListener{
            itemClickListener.onClick(it, position)
        }

        //Map에 있는 카테고리인 경우
        if(textToicon.containsKey(item.category))
            holder.icon.setImageResource(textToicon.get(item.category)!!)
    }

    override fun getItemCount(): Int = items.size


}
data class RestaurantInformation(val category: String, val restaurant: String)