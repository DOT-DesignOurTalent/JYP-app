package com.dot.jyp.model

import com.dot.jyp.R
import java.util.HashMap

object HashMapSingleTon {
    val nameToicon: Map<String, Int> = object : HashMap<String, Int>() {
        init {
            put("한식", R.drawable.ic_korean)
            put("양식", R.drawable.ic_fastfood)
            put("중식", R.drawable.ic_chinese)
            put("일식", R.drawable.ic_japanese)
            put("아시안 음식", R.drawable.ic_asian)
            put("치킨", R.drawable.ic_western)
        }
    }
}