package com.dot.jyp.model

import com.dot.jyp.R
import java.util.HashMap

object HashMapSingleTon {
    val nameToicon: Map<String, Int> = object : HashMap<String, Int>() {
        init {
            put("한식", R.drawable.ic_korean)
            put("양식", R.drawable.ic_western)
            put("중식", R.drawable.ic_chinese)
            put("일식", R.drawable.ic_japanese)
            put("분식", R.drawable.ic_snackbar)
            put("아시아음식", R.drawable.ic_asian)
            put("멕시칸", R.drawable.ic_korean)
            put("햄버거", R.drawable.ic_fastfood)
            put("피자", R.drawable.ic_korean)
            put("치킨", R.drawable.ic_korean)
            put("간식", R.drawable.ic_dessert)
            put("상관없음", R.drawable.ic_korean)
        }
    }
}