package com.dot.jyp.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitSingleTon {
    private const val BackEnd_URL = "https://tempkirrit.com"
    private const val Kakao_URL = "https://dapi.kakao.com"

    private val backEndRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BackEnd_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private val kakaoRetrofit : Retrofit = Retrofit.Builder()
            .baseUrl(Kakao_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val backEndService:BackEndService = backEndRetrofit.create(BackEndService::class.java)
    val kakaoService:KakaoService = kakaoRetrofit.create(KakaoService::class.java)
}