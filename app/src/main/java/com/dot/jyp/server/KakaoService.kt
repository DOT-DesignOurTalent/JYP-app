package com.dot.jyp.server

import com.dot.jyp.model.KakaoSearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoService {
    @Headers("Authorization: KakaoAK 32778f7d0fa7e01a073510d75f811b57")
    @GET("/v2/local/search/category.json")
    fun getRestaurants(
        @Query("category_group_code") category_group_code : String,
        @Query("x") x : String,
        @Query("y") y : String,
        @Query("radius") radius : Int
    ): Call<KakaoSearchResult>
}