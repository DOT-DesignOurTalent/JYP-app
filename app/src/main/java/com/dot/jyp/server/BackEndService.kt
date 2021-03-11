package com.dot.jyp.server

import com.dot.jyp.model.Account
import com.dot.jyp.model.UserAccount
import retrofit2.Call
import retrofit2.http.*

interface BackEndService {
    @Headers("Content-Type: application/json")
    @POST("/api/v1/user/signup")
    fun signUp(
        @Body account : Account
    ): Call<Void>

    @Headers("Content-Type: application/json")
    @POST("/api/v1/user/login")
    fun logIn(
        @Body user: UserAccount
    ): Call<Void>
}