package com.dot.jyp.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dot.jyp.R

class SelectMenuListAdapter(val items : ArrayList<RestaurantInformation>) : RecyclerView.Adapter<SelectMenuListAdapter.ViewHolder>(){

    //ClickListener
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    private lateinit var itemClickListener : OnItemClickListener

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val category = itemView.findViewById<TextView>(R.id.text_item_select_menu_category)
        val restaurant = itemView.findViewById<TextView>(R.id.text_item_select_menu_restaurants)
        val layout = itemView.findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.constraint_item_select_menu_layout)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectMenuListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_select_menu,parent, false)
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
    }

    override fun getItemCount(): Int = items.size


}
data class RestaurantInformation(val category: String, val restaurant : String)