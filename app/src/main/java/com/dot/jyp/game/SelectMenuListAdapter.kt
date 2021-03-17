package com.dot.jyp.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dot.jyp.R
import com.dot.jyp.model.HashMapSingleTon.nameToicon

class SelectMenuListAdapter(val items: ArrayList<CategoryName>, val exceptCategoryName: String) : RecyclerView.Adapter<SelectMenuListAdapter.ViewHolder>(){
    //ClickListener
    interface OnItemClickListener {
        fun onItemClick(v: View, position: Int, name : String)
    }
    private lateinit var itemClickListener : OnItemClickListener

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val categoryImage1 = itemView.findViewById<ImageView>(R.id.image_item_select_category1)
        val categoryImage2 = itemView.findViewById<ImageView>(R.id.image_item_select_category2)
        val categoryImage3 = itemView.findViewById<ImageView>(R.id.image_item_select_category3)

        val categoryText1 = itemView.findViewById<TextView>(R.id.text_item_select_category1)
        val categoryText2 = itemView.findViewById<TextView>(R.id.text_item_select_category2)
        val categoryText3 = itemView.findViewById<TextView>(R.id.text_item_select_category3)


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

        holder.categoryText1.text = item.category1
        holder.categoryImage1.setImageResource(nameToicon[item.category1]!!)
        holder.categoryText2.text = item.category2
        holder.categoryImage2.setImageResource(nameToicon[item.category2]!!)
        holder.categoryText3.text = item.category3
        holder.categoryImage3.setImageResource(nameToicon[item.category3]!!)

        if(exceptCategoryName == item.category1)
            holder.categoryImage1.alpha = 0.5f
        else {
            holder.categoryImage1.setOnClickListener {
                itemClickListener.onItemClick(it, position, item.category1)
            }
        }

        if(exceptCategoryName == item.category2)
            holder.categoryImage2.alpha = 0.5f
        else {
            holder.categoryImage2.setOnClickListener {
                itemClickListener.onItemClick(it, position, item.category2)
            }
        }

        if(exceptCategoryName == item.category3)
            holder.categoryImage3.alpha = 0.5f
        else {
            holder.categoryImage3.setOnClickListener {
                itemClickListener.onItemClick(it, position, item.category3)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}
data class CategoryName(val category1: String, val category2 : String, val category3 : String )